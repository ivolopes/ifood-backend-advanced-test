package br.com.ifood.musicservice.infrastructure.rest.client.spotify;

import br.com.ifood.musicservice.framework.configuration.FeignEncoderConfig;
import br.com.ifood.musicservice.infrastructure.rest.client.spotify.dto.PlaylistTrackResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "playlistSpotifyClient", url = "https://api.spotify.com/v1/playlists",
        configuration = FeignEncoderConfig.class )
public interface PlaylistSpotifyClient{

    @GetMapping("/{id}/tracks")
    PlaylistTrackResponseDTO findPlaylistsByCategory(@RequestHeader("Authorization") String token,
                                                     @PathVariable(value = "id") String id);


}
