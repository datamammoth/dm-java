package com.datamammoth.api;

import com.datamammoth.HttpClient;
import java.util.Map;

/** Products API -- browse hosting plans, addons, and pricing. */
public class ProductsApi {

    private final HttpClient client;

    public ProductsApi(HttpClient client) {
        this.client = client;
    }

    public Map<String, Object> list() { return list(Map.of()); }
    public Map<String, Object> list(Map<String, String> params) {
        return client.get("/products", params);
    }

    public Map<String, Object> get(String id) {
        return client.get("/products/" + id);
    }

    public Map<String, Object> addons(String id) {
        return client.get("/products/" + id + "/addons");
    }

    public Map<String, Object> options(String id) {
        return client.get("/products/" + id + "/options");
    }

    public Map<String, Object> pricing(String id) {
        return client.get("/products/" + id + "/pricing");
    }

    public Map<String, Object> categories() { return categories(Map.of()); }
    public Map<String, Object> categories(Map<String, String> params) {
        return client.get("/categories", params);
    }
}
