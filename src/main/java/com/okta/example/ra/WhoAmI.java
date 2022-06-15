package com.okta.example.ra;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WhoAmI {

    @GetMapping("/user")
    String whoAmI(UsernamePasswordAuthenticationToken token) {
        return token.toString();
    }
}
