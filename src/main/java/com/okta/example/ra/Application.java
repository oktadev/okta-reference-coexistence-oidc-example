package com.okta.example.ra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                .anyRequest().authenticated()
            )
            .oauth2Login();
        return http.build();
    }

    @Controller
    static class ProfileController {

        @GetMapping("/")
        public String home() {
            return "home";
        }

        @GetMapping("/profile")
        public ModelAndView userDetails(Authentication authentication) {
            return new ModelAndView("userProfile" , Map.of(
                    "user", authentication.getPrincipal(),
                    "simpleAuth", authentication instanceof UsernamePasswordAuthenticationToken));
        }
    }

}
