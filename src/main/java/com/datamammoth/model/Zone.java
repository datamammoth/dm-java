package com.datamammoth.model;

public record Zone(
    String id,
    String name,
    String slug,
    String country,
    String city,
    boolean isActive,
    String createdAt,
    String updatedAt
) {}
