package br.com.ifood.adminservice.infrastructure.port.application;

import br.com.ifood.adminservice.infrastructure.rest.controller.v1.user.dto.UserDto;

import javax.servlet.http.HttpServletRequest;

public interface LoginApplicationPort {
    String login(UserDto userDto, HttpServletRequest request);
}
