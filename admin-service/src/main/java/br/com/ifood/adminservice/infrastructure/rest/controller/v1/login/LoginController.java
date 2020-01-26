package br.com.ifood.adminservice.infrastructure.rest.controller.v1.login;

import br.com.ifood.adminservice.infrastructure.port.application.LoginApplicationPort;
import br.com.ifood.adminservice.infrastructure.rest.controller.v1.user.dto.UserDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/api/v1/login",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE
        })
public class LoginController {

    private LoginApplicationPort loginApplication;

    public LoginController(LoginApplicationPort loginApplication){
        this.loginApplication = loginApplication;
    }

    @PostMapping
    public ResponseEntity<String> loginUser(@RequestBody UserDto requestDTO, HttpServletRequest request) {
        String token = loginApplication.login(requestDTO, request);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+token);
        return ok().headers(headers).body(null);
    }

}
