package com.datamammoth.model;

public record Invoice(
    String id,
    String status,
    double total,
    double subtotal,
    double tax,
    String currency,
    String dueDate,
    String paidAt,
    String createdAt,
    String updatedAt
) {}
