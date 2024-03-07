package com.turing.alan.cpifp.incidentify.user.adapter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserModifyDTO {
    
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    private String surname1;
    private String surname2;
    private String role;

    public UserModifyDTO(@Email @NotBlank String email, @NotBlank String password, @NotBlank String name,
            @NotBlank String surname1, String surname2,String role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.role = role;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getSurname1() {
        return surname1;
    }
    public void setSurname1(String surname1) {
        this.surname1 = surname1;
    }
    public String getSurname2() {
        return surname2;
    }
    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
