package com.datamammoth.model;

import java.util.List;

public record Webhook(
    String id,
    String url,
    List<String> events,
    boolean active,
    String secret,
    String createdAt,
    String updatedAt
) {}
