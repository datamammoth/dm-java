package com.datamammoth.api;

import com.datamammoth.HttpClient;

import java.util.Map;

/**
 * Servers API -- provision, manage, and monitor VPS instances.
 */
public class ServersApi {

    private final HttpClient client;

    public ServersApi(HttpClient client) {
        this.client = client;
    }

    public Map<String, Object> list() { return list(Map.of()); }
    public Map<String, Object> list(Map<String, String> params) {
        return client.get("/servers", params);
    }

    public Map<String, Object> get(String id) {
        return client.get("/servers/" + id);
    }

    public Map<String, Object> create(Map<String, Object> params) {
        return client.post("/servers", params);
    }

    public Map<String, Object> update(String id, Map<String, Object> params) {
        return client.patch("/servers/" + id, params);
    }

    public Map<String, Object> delete(String id) {
        return client.delete("/servers/" + id);
    }

    public Map<String, Object> action(String id, String action, Map<String, Object> params) {
        return client.post("/servers/" + id + "/actions/" + action, params);
    }

    public Map<String, Object> action(String id, String action) {
        return action(id, action, Map.of());
    }

    public Map<String, Object> metrics(String id) { return metrics(id, Map.of()); }
    public Map<String, Object> metrics(String id, Map<String, String> params) {
        return client.get("/servers/" + id + "/metrics", params);
    }

    public Map<String, Object> events(String id) { return events(id, Map.of()); }
    public Map<String, Object> events(String id, Map<String, String> params) {
        return client.get("/servers/" + id + "/events", params);
    }

    public Map<String, Object> console(String id) {
        return client.get("/servers/" + id + "/console");
    }

    // -- Snapshots
    public Map<String, Object> listSnapshots(String id) {
        return client.get("/servers/" + id + "/snapshots");
    }

    public Map<String, Object> createSnapshot(String id, Map<String, Object> params) {
        return client.post("/servers/" + id + "/snapshots", params);
    }

    public Map<String, Object> deleteSnapshot(String serverId, String snapId) {
        return client.delete("/servers/" + serverId + "/snapshots/" + snapId);
    }

    // -- Firewall
    public Map<String, Object> getFirewall(String id) {
        return client.get("/servers/" + id + "/firewall");
    }

    public Map<String, Object> updateFirewall(String id, Map<String, Object> rules) {
        return client.put("/servers/" + id + "/firewall", rules);
    }
}
