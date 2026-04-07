package com.datamammoth.api;

import com.datamammoth.HttpClient;
import java.util.Map;

/** Billing API -- invoices, subscriptions, balance, and payment methods. */
public class BillingApi {

    private final HttpClient client;

    public BillingApi(HttpClient client) {
        this.client = client;
    }

    // -- Invoices
    public Map<String, Object> listInvoices() { return listInvoices(Map.of()); }
    public Map<String, Object> listInvoices(Map<String, String> params) {
        return client.get("/invoices", params);
    }

    public Map<String, Object> getInvoice(String id) {
        return client.get("/invoices/" + id);
    }

    public Map<String, Object> payInvoice(String id) { return payInvoice(id, Map.of()); }
    public Map<String, Object> payInvoice(String id, Map<String, Object> params) {
        return client.post("/invoices/" + id + "/pay", params);
    }

    // -- Subscriptions
    public Map<String, Object> listSubscriptions() { return listSubscriptions(Map.of()); }
    public Map<String, Object> listSubscriptions(Map<String, String> params) {
        return client.get("/subscriptions", params);
    }

    public Map<String, Object> getSubscription(String id) {
        return client.get("/subscriptions/" + id);
    }

    // -- Balance
    public Map<String, Object> getBalance() {
        return client.get("/balance");
    }

    public Map<String, Object> listTransactions() { return listTransactions(Map.of()); }
    public Map<String, Object> listTransactions(Map<String, String> params) {
        return client.get("/balance/transactions", params);
    }

    // -- Payment Methods
    public Map<String, Object> listPaymentMethods() {
        return client.get("/payment-methods");
    }

    // -- Orders
    public Map<String, Object> listOrders() { return listOrders(Map.of()); }
    public Map<String, Object> listOrders(Map<String, String> params) {
        return client.get("/orders", params);
    }

    public Map<String, Object> getOrder(String id) {
        return client.get("/orders/" + id);
    }

    // -- Promo
    public Map<String, Object> validatePromo(Map<String, Object> params) {
        return client.post("/promo/validate", params);
    }
}
