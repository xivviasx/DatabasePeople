// LoginService.java
package org.example.service;

import org.example.model.Login;
import org.example.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public Optional<Login> findByUsername(String username) {
        return loginRepository.findByUsername(username);
    }

    public void save(Login login) {
        loginRepository.save(login);
    }
}
