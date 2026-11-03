package com.example.biblioteca.dto;

public record LoginResponse(

    String accessToken,
    String tokenType,
    long expiresIn

) {}