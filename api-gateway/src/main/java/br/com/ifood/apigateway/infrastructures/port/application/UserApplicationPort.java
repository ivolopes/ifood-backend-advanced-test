package br.com.ifood.apigateway.infrastructures.port.application;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserApplicationPort {
    UserDetails findByUsername(String username, String token);
}
