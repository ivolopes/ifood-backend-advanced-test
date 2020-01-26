package br.com.ifood.apigateway.infrastructures.rest.services.adminService;

import br.com.ifood.apigateway.infrastructures.rest.services.adminService.dto.UserClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "climateServiceClient", url = "http://localhost:8080/admin-service/api/v1/users",
        fallback = UserClientFallback.class)
public interface UserClient {

    @GetMapping("/{username}")
    UserClientDTO findByUsername(@RequestHeader("Authorization") String token,
                                 @PathVariable(name = "username") String username);

}

@Component
class UserClientFallback implements UserClient{

    @Override
    public UserClientDTO findByUsername( String token, String username) {
        return UserClientDTO.builder()
                .unavailable(true)
                .build();
    }

}
