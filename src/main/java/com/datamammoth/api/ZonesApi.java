package com.datamammoth.api;

import com.datamammoth.HttpClient;
import java.util.Map;

/** Zones API -- hosting zones (regions) and available OS images. */
public class ZonesApi {

    private final HttpClient client;

    public ZonesApi(HttpClient client) {
        this.client = client;
    }

    public Map<String, Object> list() { return list(Map.of()); }
    public Map<String, Object> list(Map<String, String> params) {
        return client.get("/zones", params);
    }

    public Map<String, Object> listImages(String zoneId) { return listImages(zoneId, Map.of()); }
    public Map<String, Object> listImages(String zoneId, Map<String, String> params) {
        return client.get("/zones/" + zoneId + "/images", params);
    }
}
