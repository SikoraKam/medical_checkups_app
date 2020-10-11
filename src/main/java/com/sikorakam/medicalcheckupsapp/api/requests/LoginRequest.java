package com.sikorakam.medicalcheckupsapp.api.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginRequest {
    @NotBlank(message="Name is obligatory")
    private String email;

    @NotBlank(message="Password is obligatory")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
