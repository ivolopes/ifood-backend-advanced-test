package br.com.ifood.musicservice.infrastructure.rest.client.spotify;

import br.com.ifood.musicservice.framework.configuration.FeignEncoderConfig;
import br.com.ifood.musicservice.framework.configuration.SpotifyClientConfiguration;
import br.com.ifood.musicservice.infrastructure.rest.client.spotify.dto.LoginRequestDTO;
import br.com.ifood.musicservice.infrastructure.rest.client.spotify.dto.LoginResponseDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "loginSpotifyClient", url = "https://accounts.spotify.com/api",
        configuration = {SpotifyClientConfiguration.class, FeignEncoderConfig.class} )
public interface LoginSpotifyClient {

    @PostMapping(path = "/token", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @Headers("Content-Type: userInterface/x-www-form-urlencoded")
    LoginResponseDTO autenticar(@RequestBody LoginRequestDTO model);

}
