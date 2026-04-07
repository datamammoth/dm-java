package com.datamammoth.api;

import com.datamammoth.HttpClient;
import java.util.Map;

/** Support API -- tickets, replies, departments, and knowledge base. */
public class SupportApi {

    private final HttpClient client;

    public SupportApi(HttpClient client) {
        this.client = client;
    }

    public Map<String, Object> listTickets() { return listTickets(Map.of()); }
    public Map<String, Object> listTickets(Map<String, String> params) {
        return client.get("/tickets", params);
    }

    public Map<String, Object> getTicket(String id) {
        return client.get("/tickets/" + id);
    }

    public Map<String, Object> createTicket(Map<String, Object> params) {
        return client.post("/tickets", params);
    }

    public Map<String, Object> replyToTicket(String id, Map<String, Object> params) {
        return client.post("/tickets/" + id + "/replies", params);
    }

    public Map<String, Object> ticketFeedback(String id, Map<String, Object> params) {
        return client.post("/tickets/" + id + "/feedback", params);
    }

    public Map<String, Object> listDepartments() {
        return client.get("/tickets/departments");
    }

    public Map<String, Object> listArticles() { return listArticles(Map.of()); }
    public Map<String, Object> listArticles(Map<String, String> params) {
        return client.get("/kb/articles", params);
    }

    public Map<String, Object> getArticle(String slug) {
        return client.get("/kb/articles/" + slug);
    }
}
