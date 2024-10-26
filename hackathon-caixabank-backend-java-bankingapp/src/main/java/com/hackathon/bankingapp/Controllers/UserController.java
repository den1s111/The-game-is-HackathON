package com.hackathon.bankingapp.Controllers;

import com.hackathon.bankingapp.Entities.User;
import com.hackathon.bankingapp.Services.UserService;
import com.hackathon.bankingapp.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) 
    {
        try {
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.ok(registeredUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginRequest) 
    {
        String identifier = loginRequest.get("identifier");
        String password = loginRequest.get("password");

        // Attempt to find the user by email or account number
        Optional<User> userOpt = userService.findUserByEmail(identifier);
        if (userOpt.isEmpty()) 
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found for the given identifier: " + identifier);
        }

        User user = userOpt.get();
        
        // Validate password
        if (!passwordEncoder.matches(password, user.getPassword())) 
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad credentials");
        }

        // Generate JWT token on successful login
        String token = jwtUtil.generateToken(user.getAccountNumber());
        return ResponseEntity.ok(Map.of("token", token));
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String token) 
    {
        String accountNumber = jwtUtil.extractUsername(token.replace("Bearer ", ""));
        Optional<User> userOpt = userService.findUserByAccountNumber(accountNumber);

        // Explicitly handle both cases
        if (userOpt.isPresent()) 
        {
            return ResponseEntity.ok(userOpt.get());
        } 
        else 
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }
}
