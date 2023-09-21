package com.example.invoice.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRegisterType {
    private String fullName;
    private String email;
    private String password;
    private String position;
    private String consultant;
}
