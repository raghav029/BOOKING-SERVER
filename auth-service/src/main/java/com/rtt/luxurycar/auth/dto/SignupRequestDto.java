package com.rtt.luxurycar.auth.dto;

import jakarta.validation.constraints.NotBlank;

public class SignupRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String city;

    public SignupRequestDto() {}

    public SignupRequestDto(String name, String email, String password, String city) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
