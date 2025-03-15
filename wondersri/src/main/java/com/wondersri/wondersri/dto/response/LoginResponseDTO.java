package com.wondersri.wondersri.dto.response;

public class LoginResponseDTO {
    private String token;
    private String message;

    // Constructors
    public LoginResponseDTO() {}
    public LoginResponseDTO(String token, String message) {
        this.token = token;
        this.message = message;
    }

    // Getters and Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}