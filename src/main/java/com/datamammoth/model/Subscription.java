package com.datamammoth.model;

public record Subscription(
    String id,
    String status,
    String productId,
    String planName,
    String billingCycle,
    Double amount,
    String currency,
    String nextDueDate,
    String cancelledAt,
    String createdAt,
    String updatedAt
) {}
