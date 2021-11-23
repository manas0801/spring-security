package com.smartbit.sswoa.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name="userotp")
public class UserOtp {
    @Id
    private String username;
    private String code;

    public UserOtp(String username, String code) {
        this.username = username;
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UserOtp() {

    }
}
