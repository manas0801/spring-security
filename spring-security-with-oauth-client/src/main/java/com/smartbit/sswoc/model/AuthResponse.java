package com.smartbit.sswoc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String access_token;
    private String token_type;
    private String refresh_token;
    private long expires_in;
}
