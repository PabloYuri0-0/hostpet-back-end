package com.hostpet.hostpet.entity;

public record RegisterDTO(String username, String email, String password, UserRole role) {
}
