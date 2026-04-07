package com.datamammoth;

import com.datamammoth.api.*;

import java.time.Duration;

/**
 * DataMammoth Java SDK -- official client for the DataMammoth API v2.
 *
 * <pre>{@code
 * var dm = new DataMammoth("dm_live_...");
 * var servers = dm.servers().list();
 * var server = dm.servers().get("srv_abc123");
 * }</pre>
 */
public class DataMammoth {

    private final HttpClient httpClient;
    private final ServersApi servers;
    private final ProductsApi products;
    private final BillingApi billing;
    private final SupportApi support;
    private final AccountApi account;
    private final AdminApi admin;
    private final AffiliateApi affiliate;
    private final WebhooksApi webhooks;
    private final TasksApi tasks;
    private final ZonesApi zones;

    /**
     * Create a new DataMammoth client with default settings.
     *
     * @param apiKey Your DataMammoth API key (dm_live_... or dm_test_...)
     */
    public DataMammoth(String apiKey) {
        this(apiKey, "https://app.datamammoth.com/api/v2", 3, Duration.ofSeconds(30));
    }

    /**
     * Create a new DataMammoth client with custom settings.
     *
     * @param apiKey     API key
     * @param baseUrl    Base URL (override for self-hosted or staging)
     * @param maxRetries Max retry attempts for 429/5xx
     * @param timeout    Request timeout
     */
    public DataMammoth(String apiKey, String baseUrl, int maxRetries, Duration timeout) {
        this.httpClient = new HttpClient(apiKey, baseUrl, maxRetries, timeout);
        this.servers = new ServersApi(httpClient);
        this.products = new ProductsApi(httpClient);
        this.billing = new BillingApi(httpClient);
        this.support = new SupportApi(httpClient);
        this.account = new AccountApi(httpClient);
        this.admin = new AdminApi(httpClient);
        this.affiliate = new AffiliateApi(httpClient);
        this.webhooks = new WebhooksApi(httpClient);
        this.tasks = new TasksApi(httpClient);
        this.zones = new ZonesApi(httpClient);
    }

    public ServersApi servers() { return servers; }
    public ProductsApi products() { return products; }
    public BillingApi billing() { return billing; }
    public SupportApi support() { return support; }
    public AccountApi account() { return account; }
    public AdminApi admin() { return admin; }
    public AffiliateApi affiliate() { return affiliate; }
    public WebhooksApi webhooks() { return webhooks; }
    public TasksApi tasks() { return tasks; }
    public ZonesApi zones() { return zones; }

    /** Access the underlying HTTP client for custom requests. */
    public HttpClient getHttpClient() { return httpClient; }
}
