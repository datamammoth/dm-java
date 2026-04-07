package com.datamammoth.api;

import com.datamammoth.HttpClient;
import com.datamammoth.exception.DataMammothException;

import java.util.Map;

/** Tasks API -- track async operations (server provisioning, etc.). */
public class TasksApi {

    private final HttpClient client;

    public TasksApi(HttpClient client) {
        this.client = client;
    }

    public Map<String, Object> list() { return list(Map.of()); }
    public Map<String, Object> list(Map<String, String> params) { return client.get("/tasks", params); }
    public Map<String, Object> get(String id) { return client.get("/tasks/" + id); }

    /**
     * Poll a task until it completes or times out.
     *
     * @param id           Task ID
     * @param intervalMs   Milliseconds between polls
     * @param timeoutMs    Maximum wait time
     * @return Completed task response
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> await(String id, long intervalMs, long timeoutMs) {
        long start = System.currentTimeMillis();

        while (true) {
            var task = get(id);
            var data = (Map<String, Object>) task.getOrDefault("data", Map.of());
            String status = (String) data.getOrDefault("status", "unknown");

            if ("completed".equals(status) || "failed".equals(status)) {
                return task;
            }

            if (System.currentTimeMillis() - start >= timeoutMs) {
                throw new DataMammothException("Task " + id + " timed out after " + timeoutMs + "ms", 408, null, null);
            }

            try {
                Thread.sleep(intervalMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new DataMammothException("Interrupted while awaiting task", 0, null, null, e);
            }
        }
    }

    public Map<String, Object> await(String id) {
        return await(id, 2000, 300_000);
    }
}
