package com.datamammoth.api;

import com.datamammoth.HttpClient;
import java.util.Map;

/** Account API -- profile, API keys, sessions, 2FA, notifications. */
public class AccountApi {

    private final HttpClient client;

    public AccountApi(HttpClient client) {
        this.client = client;
    }

    public Map<String, Object> me() { return client.get("/me"); }

    public Map<String, Object> updateProfile(Map<String, Object> params) {
        return client.patch("/me", params);
    }

    public Map<String, Object> changePassword(Map<String, Object> params) {
        return client.post("/me/change-password", params);
    }

    public Map<String, Object> listApiKeys() { return client.get("/me/api-keys"); }

    public Map<String, Object> createApiKey(Map<String, Object> params) {
        return client.post("/me/api-keys", params);
    }

    public Map<String, Object> deleteApiKey(String id) {
        return client.delete("/me/api-keys/" + id);
    }

    public Map<String, Object> listSessions() { return client.get("/me/sessions"); }

    public Map<String, Object> revokeSession(String id) {
        return client.delete("/me/sessions/" + id);
    }

    public Map<String, Object> get2fa() { return client.get("/me/2fa"); }

    public Map<String, Object> update2fa(Map<String, Object> params) {
        return client.post("/me/2fa", params);
    }

    public Map<String, Object> listNotifications() { return listNotifications(Map.of()); }
    public Map<String, Object> listNotifications(Map<String, String> params) {
        return client.get("/me/notifications", params);
    }

    public Map<String, Object> listActivity() { return listActivity(Map.of()); }
    public Map<String, Object> listActivity(Map<String, String> params) {
        return client.get("/me/activity", params);
    }
}
