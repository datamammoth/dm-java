package com.datamammoth.model;

public record Ticket(
    String id,
    String subject,
    String status,
    String priority,
    String departmentId,
    String lastReplyAt,
    String createdAt,
    String updatedAt
) {}
