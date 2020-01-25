package br.com.ifood.musicservice.infrastructure.rest.client.spotify;

import br.com.ifood.musicservice.framework.configuration.FeignEncoderConfig;
import br.com.ifood.musicservice.infrastructure.rest.client.spotify.dto.PlaylistResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "categoriesSpotifyClient", url = "https://api.spotify.com/v1/browse/categories",
        configuration = FeignEncoderConfig.class )
public interface CategorieSpotityClient {

    @GetMapping("/{category}/playlists")
    PlaylistResponseDTO findPlaylistsByCategory(@RequestHeader("Authorization") String token,
                                                       @PathVariable(value = "category") String category);

}
