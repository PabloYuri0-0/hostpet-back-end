package com.hostpet.hostpet.dtos;

import com.hostpet.hostpet.entity.UserRole;

public record RegisterDTO(String username, String email, String password, UserRole role) {
}
