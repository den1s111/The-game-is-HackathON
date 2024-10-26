package com.hackathon.bankingapp.Entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

public class User 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String email;
    private String password; // Stored as hashed
    private String address;
    private String phoneNumber;
    private String accountNumber;
    
    public User() 
    {
        this.accountNumber = UUID.randomUUID().toString();
    }

    // Getters and setters
    public void setId(UUID id)
    {
        this.id = id;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public UUID getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public String getEmail()
    {
        return email;
    }
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public String getAddress()
    {
        return address;
    }
    public String getAccountNumber()
    {
        return accountNumber;
    }
    public String getPassword()
    {
        return password;
    }
}
