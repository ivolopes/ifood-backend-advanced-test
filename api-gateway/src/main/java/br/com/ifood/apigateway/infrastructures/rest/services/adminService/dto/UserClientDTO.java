package br.com.ifood.apigateway.infrastructures.rest.services.adminService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@Builder
@AllArgsConstructor(onConstructor_ = @Builder)
public class UserClientDTO {

    private String fullname;
    private String username;
    private String password;
    private boolean unavailable;
    private List<String> roles;

}
