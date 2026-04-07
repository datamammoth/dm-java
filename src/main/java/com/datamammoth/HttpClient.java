package com.datamammoth;

import com.datamammoth.exception.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * Low-level HTTP client with authentication, retry logic, and error mapping.
 * Uses Java 11+ HttpClient internally.
 */
public class HttpClient {

    private final java.net.http.HttpClient client;
    private final String apiKey;
    private final String baseUrl;
    private final int maxRetries;
    private final Gson gson;

    public HttpClient(String apiKey, String baseUrl, int maxRetries, Duration timeout) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl.replaceAll("/+$", "");
        this.maxRetries = maxRetries;
        this.gson = new Gson();
        this.client = java.net.http.HttpClient.newBuilder()
                .connectTimeout(timeout)
                .build();
    }

    public Map<String, Object> get(String path) {
        return get(path, Map.of());
    }

    public Map<String, Object> get(String path, Map<String, String> query) {
        String url = buildUrl(path, query);
        var request = newRequest(url).GET().build();
        return execute(request);
    }

    public Map<String, Object> post(String path, Map<String, Object> body) {
        var request = newRequest(buildUrl(path, Map.of()))
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(body)))
                .build();
        return execute(request);
    }

    public Map<String, Object> post(String path) {
        return post(path, Map.of());
    }

    public Map<String, Object> patch(String path, Map<String, Object> body) {
        var request = newRequest(buildUrl(path, Map.of()))
                .method("PATCH", HttpRequest.BodyPublishers.ofString(gson.toJson(body)))
                .build();
        return execute(request);
    }

    public Map<String, Object> put(String path, Map<String, Object> body) {
        var request = newRequest(buildUrl(path, Map.of()))
                .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(body)))
                .build();
        return execute(request);
    }

    public Map<String, Object> delete(String path) {
        var request = newRequest(buildUrl(path, Map.of()))
                .DELETE()
                .build();
        return execute(request);
    }

    Gson getGson() {
        return gson;
    }

    // ── Internal ──────────────────────────────────────────────────

    private HttpRequest.Builder newRequest(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + apiKey)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("User-Agent", "datamammoth-java/0.1.0");
    }

    private String buildUrl(String path, Map<String, String> query) {
        path = path.startsWith("/") ? path : "/" + path;
        StringBuilder sb = new StringBuilder(baseUrl).append(path);
        if (!query.isEmpty()) {
            sb.append("?");
            query.forEach((k, v) -> sb.append(k).append("=").append(v).append("&"));
            sb.setLength(sb.length() - 1); // remove trailing &
        }
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> execute(HttpRequest request) {
        int attempts = 0;

        while (true) {
            try {
                var response = client.send(request, HttpResponse.BodyHandlers.ofString());
                int status = response.statusCode();

                if (status >= 200 && status < 300) {
                    String body = response.body();
                    if (body == null || body.isBlank()) return Map.of();
                    return gson.fromJson(body, new TypeToken<Map<String, Object>>() {}.getType());
                }

                // Retry on 429 and 5xx
                if ((status == 429 || status >= 500) && attempts < maxRetries) {
                    attempts++;
                    long delay = (status == 429)
                            ? parseRetryAfter(response) * 1000L
                            : (long) (1000 * Math.pow(2, attempts));
                    Thread.sleep(delay);
                    continue;
                }

                // Parse error response
                Map<String, Object> errorBody = Map.of();
                try {
                    errorBody = gson.fromJson(response.body(), new TypeToken<Map<String, Object>>() {}.getType());
                } catch (Exception ignored) {}

                var errors = (List<Map<String, String>>) errorBody.getOrDefault("errors", List.of());
                var meta = (Map<String, Object>) errorBody.getOrDefault("meta", Map.of());
                String requestId = meta.getOrDefault("request_id", "").toString();
                String errorCode = !errors.isEmpty() ? errors.get(0).getOrDefault("code", "") : "";
                String message = !errors.isEmpty() ? errors.get(0).getOrDefault("message", "API error") : "API error";

                throw switch (status) {
                    case 401, 403 -> new AuthException(message, status, requestId, errorCode);
                    case 404 -> new NotFoundException(message, requestId, errorCode);
                    case 429 -> new RateLimitException(message, requestId, (int) parseRetryAfter(response));
                    case 400, 422 -> new ValidationException(message, status, requestId, errors);
                    default -> new DataMammothException(message, status, requestId, errorCode);
                };

            } catch (DataMammothException e) {
                throw e;
            } catch (IOException | InterruptedException e) {
                if (attempts < maxRetries) {
                    attempts++;
                    try { Thread.sleep((long) (1000 * Math.pow(2, attempts))); } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw new DataMammothException("Request interrupted", 0, null, null, ie);
                    }
                    continue;
                }
                throw new DataMammothException("Request failed: " + e.getMessage(), 0, null, null, e);
            }
        }
    }

    private long parseRetryAfter(HttpResponse<String> response) {
        return response.headers().firstValue("Retry-After")
                .map(Long::parseLong)
                .orElse(60L);
    }
}
