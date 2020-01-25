package br.com.ifood.musicservice.infrastructure.rest.client.spotify.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(onConstructor_ = @Builder)
public class LoginRequestDTO {

    private String grant_type;

}
