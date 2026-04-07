package com.datamammoth.model;

public record Task(
    String id,
    String type,
    String status,
    String resourceType,
    String resourceId,
    String startedAt,
    String completedAt,
    Object result,
    String error,
    String createdAt
) {}
