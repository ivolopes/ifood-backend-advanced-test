package br.com.ifood.apigateway.infrastructures.port.data;

import br.com.ifood.apigateway.infrastructures.rest.services.adminService.dto.UserClientDTO;

public interface UserDataPort {
    UserClientDTO findByUsername(String username);
}
