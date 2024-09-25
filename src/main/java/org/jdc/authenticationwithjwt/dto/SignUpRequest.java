package org.jdc.authenticationwithjwt.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignUpRequest(
        @NotBlank(message = "Username cannot be blank")
        String name,
        @Email(message = "Invalid Email format")
        @NotBlank(message = "Email cannot be blanked")
        String email,
        @NotBlank(message = "Password cannot be blanked")
        String password) {

}
