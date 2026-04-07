package com.datamammoth.model;

/**
 * Represents a provisioned VPS instance.
 */
public record Server(
    String id,
    String hostname,
    String label,
    String status,
    String ipAddress,
    String ipv6Address,
    String region,
    String osImage,
    String plan,
    ServerSpecs specs,
    String provisionedAt,
    String createdAt,
    String updatedAt
) {}
