package org.jdc.authenticationwithjwt.dto;

import lombok.Data;

public record LoginResponse(String email, String token) {
}
