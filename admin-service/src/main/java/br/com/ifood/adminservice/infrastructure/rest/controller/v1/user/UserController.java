package br.com.ifood.adminservice.infrastructure.rest.controller.v1.user;

import br.com.ifood.adminservice.infrastructure.port.application.UserApplicationPort;
import br.com.ifood.adminservice.infrastructure.rest.controller.v1.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/v1/users",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE
        })
public class UserController {

        private UserApplicationPort userApplication;

        @Autowired
        public UserController(UserApplicationPort userApplication){
                this.userApplication = userApplication;
        }

        @GetMapping("{username}")
        public ResponseEntity<UserDto> findByUsername(@PathVariable(name = "username") String username){
                UserDto userDto = userApplication.findByUsername(username);
                return ResponseEntity.ok(userDto);
        }
}
