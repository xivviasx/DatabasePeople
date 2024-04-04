// LoginController.java
package org.example.controller;

import org.example.model.Login;
import org.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<Login> getLogin(@PathVariable String username) {
        return loginService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody Login loginModel) {
        String username = loginModel.getUsername();
        String password = loginModel.getPassword();

        Login user = loginService.findByUsername(username).orElse(null);

        if (user != null && user.getPassword().equals(password)) {
            String userType = user.getTyp_uzytkownika();
            return ResponseEntity.ok(userType);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Login newLogin) {
        // Check if the username already exists
        if (loginService.findByUsername(newLogin.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }

        // Save the new user to the database
        loginService.save(newLogin);

        return ResponseEntity.ok("User registered successfully");
    }
}
