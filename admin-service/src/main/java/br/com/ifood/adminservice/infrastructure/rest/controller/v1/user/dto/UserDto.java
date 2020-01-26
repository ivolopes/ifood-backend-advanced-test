package br.com.ifood.adminservice.infrastructure.rest.controller.v1.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor(onConstructor_ = @Builder)
public class UserDto {

    private Integer id;

    private String fulllname;

    private String username;

    private String password;

    public UserDto(){

    }

}
