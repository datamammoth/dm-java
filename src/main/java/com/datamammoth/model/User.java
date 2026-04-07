package com.datamammoth.model;

public record User(
    String id,
    String name,
    String email,
    String role,
    String status,
    boolean twoFactorEnabled,
    String lastLoginAt,
    String createdAt,
    String updatedAt
) {}
