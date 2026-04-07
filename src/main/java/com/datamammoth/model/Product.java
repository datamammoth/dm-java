package com.datamammoth.model;

public record Product(
    String id,
    String name,
    String slug,
    String description,
    String categoryId,
    String type,
    boolean isActive,
    ServerSpecs specs,
    String createdAt,
    String updatedAt
) {}
