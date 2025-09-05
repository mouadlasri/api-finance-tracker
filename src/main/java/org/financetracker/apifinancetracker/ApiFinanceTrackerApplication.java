package org.financetracker.apifinancetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ApiFinanceTrackerApplication {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode("password123");
        System.out.println(hashedPassword);
        SpringApplication.run(ApiFinanceTrackerApplication.class, args);
    }

}
