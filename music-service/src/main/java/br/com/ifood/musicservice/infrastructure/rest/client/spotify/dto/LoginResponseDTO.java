package br.com.ifood.musicservice.infrastructure.rest.client.spotify.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(onConstructor_ = @Builder)
public class LoginResponseDTO {

    private String access_token;

    private String token_type;

    private Integer expires_in;

    private String scope;

}
