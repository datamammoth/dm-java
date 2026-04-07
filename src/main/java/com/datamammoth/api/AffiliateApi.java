package com.datamammoth.api;

import com.datamammoth.HttpClient;
import java.util.Map;

/** Affiliate API -- referral links, commissions, payouts, and materials. */
public class AffiliateApi {

    private final HttpClient client;

    public AffiliateApi(HttpClient client) {
        this.client = client;
    }

    public Map<String, Object> me() { return client.get("/affiliate/me"); }

    public Map<String, Object> listCommissions() { return listCommissions(Map.of()); }
    public Map<String, Object> listCommissions(Map<String, String> params) {
        return client.get("/affiliate/commissions", params);
    }

    public Map<String, Object> listReferrals() { return listReferrals(Map.of()); }
    public Map<String, Object> listReferrals(Map<String, String> params) {
        return client.get("/affiliate/referrals", params);
    }

    public Map<String, Object> requestPayout(Map<String, Object> params) {
        return client.post("/affiliate/payout-request", params);
    }

    public Map<String, Object> listMaterials() {
        return client.get("/affiliate/materials");
    }
}
