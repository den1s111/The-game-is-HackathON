package com.hackathon.bankingapp.Services;

import com.hackathon.bankingapp.Entities.User;
import com.hackathon.bankingapp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService 
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User registerUser(User user) 
    {
        // Check if email or phone number exists
        if (userRepository.findByEmail(user.getEmail()).isPresent() || 
            userRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent()) 
        {
            throw new IllegalArgumentException("User with given email or phone number already exists");
        }
        
        // Hash password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        return userRepository.save(user);
    }

    public Optional<User> findUserByEmail(String email) 
    {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findUserByAccountNumber(String accountNumber) 
    {
        return userRepository.findByAccountNumber(accountNumber);
    }
}
