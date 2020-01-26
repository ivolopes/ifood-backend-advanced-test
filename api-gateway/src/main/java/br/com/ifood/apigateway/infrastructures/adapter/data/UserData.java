package br.com.ifood.apigateway.infrastructures.adapter.data;

import br.com.ifood.apigateway.infrastructures.port.data.UserDataPort;
import br.com.ifood.apigateway.infrastructures.rest.services.adminService.UserClient;
import br.com.ifood.apigateway.infrastructures.rest.services.adminService.dto.UserClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserData implements UserDataPort {

    private UserClient userClient;

    @Autowired
    public UserData(UserClient userClient){
        this.userClient = userClient;
    }

    @Override
    public UserClientDTO findByUsername(String username, String token) {
        return userClient.findByUsername(token, username);
    }

}
