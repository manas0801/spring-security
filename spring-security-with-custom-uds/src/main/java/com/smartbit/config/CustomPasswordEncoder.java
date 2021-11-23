package com.smartbit.config;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class CustomPasswordEncoder implements PasswordEncoder {


    @Override
    public String encode(CharSequence charSequence) {
        return hashSha512(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        String hashedPwd= hashSha512(charSequence.toString());
        System.out.println(hashedPwd);
        return hashedPwd.equals(s);

    }



    private String hashSha512(String input){

        StringBuilder result = new StringBuilder();
        try {

            MessageDigest md= MessageDigest.getInstance("SHA-512");
            byte[]digested= md.digest(input.getBytes());
            for (int i = 0; i < digested.length; i++) {
                result.append(Integer.toHexString(0xFF & digested[i])); }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Bad algorithm");
        }
        return result.toString();

    }
}
