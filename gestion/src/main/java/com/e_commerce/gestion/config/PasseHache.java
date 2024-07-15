package com.e_commerce.gestion.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasseHache {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("1234"));  // Encode your passwords here
    }
}
