package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.entity.User;
import com.example.todo.payloads.requests.LoginRequest;
import com.example.todo.payloads.responses.MessageResponse;
import com.example.todo.payloads.responses.jwtResponse;
import com.example.todo.repository.UserRepository;
import com.example.todo.security.jwt.JwtUtils;

@CrossOrigin(origins="*")
@RestController
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;
    
    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){

        if(userRepository.existsByUsername(user.getUsername())){
            return ResponseEntity.badRequest().body( new MessageResponse("Username is already taken"));
        }

        if(userRepository.existsByEmail(user.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse("Email already in use"));
        }

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(newUser);

        return ResponseEntity.ok(new MessageResponse("User Created successfully"));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?>  login(@RequestBody LoginRequest request){

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtUtils.generateJwtToken(authentication);

            User user = userRepository.findByUsername(request.getUsername()).orElse(null);

            return ResponseEntity.ok(new jwtResponse(jwt, user.getId(), user.getUsername(), user.getEmail()));
    }



}
