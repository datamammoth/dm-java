package com.datamammoth.api;

import com.datamammoth.HttpClient;
import java.util.Map;

/** Webhooks API -- manage webhook endpoints, deliveries, and events. */
public class WebhooksApi {

    private final HttpClient client;

    public WebhooksApi(HttpClient client) {
        this.client = client;
    }

    public Map<String, Object> list() { return list(Map.of()); }
    public Map<String, Object> list(Map<String, String> params) { return client.get("/webhooks", params); }
    public Map<String, Object> get(String id) { return client.get("/webhooks/" + id); }
    public Map<String, Object> create(Map<String, Object> params) { return client.post("/webhooks", params); }
    public Map<String, Object> update(String id, Map<String, Object> params) { return client.patch("/webhooks/" + id, params); }
    public Map<String, Object> delete(String id) { return client.delete("/webhooks/" + id); }

    public Map<String, Object> listDeliveries(String id) { return listDeliveries(id, Map.of()); }
    public Map<String, Object> listDeliveries(String id, Map<String, String> params) {
        return client.get("/webhooks/" + id + "/deliveries", params);
    }

    public Map<String, Object> test(String id) { return client.post("/webhooks/" + id + "/test"); }
    public Map<String, Object> listEvents() { return client.get("/webhooks/events"); }
}
