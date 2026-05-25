package com.example.loginRegistration.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmailRequest {

    @NotBlank(message = "email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    public EmailRequest() {
    }

    public EmailRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
