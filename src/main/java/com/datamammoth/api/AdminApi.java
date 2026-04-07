package com.datamammoth.api;

import com.datamammoth.HttpClient;
import java.util.Map;

/** Admin API -- user management, roles, tenants, audit log, dashboard. */
public class AdminApi {

    private final HttpClient client;

    public AdminApi(HttpClient client) {
        this.client = client;
    }

    // -- Users
    public Map<String, Object> listUsers() { return listUsers(Map.of()); }
    public Map<String, Object> listUsers(Map<String, String> params) { return client.get("/admin/users", params); }
    public Map<String, Object> getUser(String id) { return client.get("/admin/users/" + id); }
    public Map<String, Object> updateUser(String id, Map<String, Object> params) { return client.patch("/admin/users/" + id, params); }

    // -- Roles
    public Map<String, Object> listRoles() { return client.get("/admin/roles"); }
    public Map<String, Object> getRole(String id) { return client.get("/admin/roles/" + id); }
    public Map<String, Object> createRole(Map<String, Object> params) { return client.post("/admin/roles", params); }
    public Map<String, Object> updateRole(String id, Map<String, Object> params) { return client.patch("/admin/roles/" + id, params); }
    public Map<String, Object> deleteRole(String id) { return client.delete("/admin/roles/" + id); }

    // -- Tenants
    public Map<String, Object> listTenants() { return listTenants(Map.of()); }
    public Map<String, Object> listTenants(Map<String, String> params) { return client.get("/admin/tenants", params); }
    public Map<String, Object> getTenant(String id) { return client.get("/admin/tenants/" + id); }
    public Map<String, Object> updateTenant(String id, Map<String, Object> params) { return client.patch("/admin/tenants/" + id, params); }

    // -- Admin-scoped lists
    public Map<String, Object> listServers() { return listServers(Map.of()); }
    public Map<String, Object> listServers(Map<String, String> params) { return client.get("/admin/servers", params); }
    public Map<String, Object> listInvoices() { return listInvoices(Map.of()); }
    public Map<String, Object> listInvoices(Map<String, String> params) { return client.get("/admin/invoices", params); }
    public Map<String, Object> listTickets() { return listTickets(Map.of()); }
    public Map<String, Object> listTickets(Map<String, String> params) { return client.get("/admin/tickets", params); }
    public Map<String, Object> getTicket(String id) { return client.get("/admin/tickets/" + id); }
    public Map<String, Object> listLeads() { return listLeads(Map.of()); }
    public Map<String, Object> listLeads(Map<String, String> params) { return client.get("/admin/leads", params); }

    // -- Audit & Dashboard
    public Map<String, Object> listAuditLog() { return listAuditLog(Map.of()); }
    public Map<String, Object> listAuditLog(Map<String, String> params) { return client.get("/admin/audit-log", params); }
    public Map<String, Object> dashboardStats() { return client.get("/admin/dashboard/stats"); }

    // -- Masquerade
    public Map<String, Object> masquerade(String userId) { return client.post("/admin/masquerade/" + userId); }
}
