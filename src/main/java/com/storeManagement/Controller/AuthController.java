package com.storeManagement.Controller;

import com.storeManagement.DTOs.UserDto;
import com.storeManagement.Service.UserServiceImpl;
import com.storeManagement.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://store-mgmt-ang-sbjava.netlify.app","http://localhost:3000"})
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            
            System.out.println("Attempting to authenticate user: " + authenticationRequest.getUsername());

            // Authenticate user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );

            // Load user details
            final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
            System.out.println("User authenticated: " + userDetails.getUsername());

            // Generate JWT
            final String jwt = jwtUtil.generateToken(userDetails);

            UserDto userDto = userService.findUserIdByUsername(authenticationRequest.getUsername());
            // Return JWT in response
            return ResponseEntity.ok(new AuthenticationResponse(jwt, userDto.getUserId(), userDto.getUserName()));
        } catch (AuthenticationException e) {
            // Handle authentication failure
            System.out.println("Authentication failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}


