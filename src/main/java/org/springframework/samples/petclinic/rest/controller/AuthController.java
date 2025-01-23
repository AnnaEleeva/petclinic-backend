package org.springframework.samples.petclinic.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/api/auth")
public class AuthController {


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> authenticateUser(@RequestBody Map<String, Object> credentials) {
        String username = (String) credentials.get("username");
        String password = (String) credentials.get("password");

        String token = Base64.getEncoder().encodeToString((username + ":" + password).getBytes(UTF_8));

        Map<String, String> response = new HashMap<>();
        response.put("authToken", token);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    //        String username = (String) credentials.get("username");
//        String password = (String) credentials.get("password");
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
//        Authentication authentication = authenticationManager.authenticate(token);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return ResponseEntity.ok("Successfully logged in");

//    @PostMapping("/logout")
//    public ResponseEntity<String> logout() {
//        SecurityContextHolder.clearContext();
//        return ResponseEntity.ok("Logged out successfully");
//    }
}
