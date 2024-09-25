package org.jdc.authenticationwithjwt.service;

import lombok.RequiredArgsConstructor;
import org.jdc.authenticationwithjwt.dao.UserDao;
import org.jdc.authenticationwithjwt.dto.LoginRequest;
import org.jdc.authenticationwithjwt.dto.LoginResponse;
import org.jdc.authenticationwithjwt.dto.SignUpRequest;
import org.jdc.authenticationwithjwt.entity.User;
import org.jdc.authenticationwithjwt.jwt.JwtHelper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;

    public void signUp(SignUpRequest request) {
        if(userDao.findByEmail(request.email()).isPresent()) {
            throw new DuplicateKeyException("Email already exists");
        }
        User user = new User(request.name(), request.email(), passwordEncoder.encode(request.password()));
        userDao.save(user);
    }

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        String token = jwtHelper.generateToken(request.email());
        return new LoginResponse(request.email(), token);
    }
}
