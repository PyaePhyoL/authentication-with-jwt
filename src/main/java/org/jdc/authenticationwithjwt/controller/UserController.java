package org.jdc.authenticationwithjwt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jdc.authenticationwithjwt.dto.LoginRequest;
import org.jdc.authenticationwithjwt.dto.LoginResponse;
import org.jdc.authenticationwithjwt.dto.SignUpRequest;
import org.jdc.authenticationwithjwt.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@Valid @RequestBody SignUpRequest request) {
        userService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello World!");
    }
}
