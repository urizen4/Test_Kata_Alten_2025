package com.producttrial.producttrial.controller;

import com.producttrial.producttrial.dto.LoginRequest;
import com.producttrial.producttrial.dto.RegisterRequest;
import com.producttrial.producttrial.model.User;
import com.producttrial.producttrial.security.jwt.JwtUtil;
import com.producttrial.producttrial.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request)
    {
        User user = new User();
        user.setUsername(request.username);
        user.setFirstname(request.firstname);
        user.setEmail(request.email);
        user.setPassword(request.password);
        User saved = userService.registerUser(user);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> login(@RequestBody LoginRequest request)
    {
        try
        {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email, request.password));

        } catch (AuthenticationException ex)
        {
            return ResponseEntity.status(401).body("Email ou mot de passe invalide");
        }
        String token = jwtUtil.generateToken(request.email);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
