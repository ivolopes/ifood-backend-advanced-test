package br.com.ifood.adminservice.infrastructure.port.application;

import br.com.ifood.adminservice.infrastructure.rest.controller.v1.user.dto.UserDto;

public interface UserApplicationPort {

    UserDto save(String fullname, String username, String password);

    UserDto findByUsername(String username);

}
