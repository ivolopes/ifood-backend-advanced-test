package br.com.ifood.musicservice.infrastructure.adapter.data;

import br.com.ifood.musicservice.infrastructure.port.data.MusicDataPort;
import br.com.ifood.musicservice.infrastructure.rest.client.spotify.CategorieSpotityClient;
import br.com.ifood.musicservice.infrastructure.rest.client.spotify.LoginSpotifyClient;
import br.com.ifood.musicservice.infrastructure.rest.client.spotify.PlaylistSpotifyClient;
import br.com.ifood.musicservice.infrastructure.rest.client.spotify.dto.*;
import br.com.ifood.musicservice.infrastructure.rest.controller.dto.GenericDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MusicData implements MusicDataPort {

    private CategorieSpotityClient categorieSpotityClient;
    private PlaylistSpotifyClient playlistSpotifyClient;
    private LoginSpotifyClient loginSpotifyClient;

    @Autowired
    public MusicData(CategorieSpotityClient categorieSpotityClient,
                     PlaylistSpotifyClient playlistSpotifyClient,
                     LoginSpotifyClient loginSpotifyClient){
        this.categorieSpotityClient = categorieSpotityClient;
        this.playlistSpotifyClient = playlistSpotifyClient;
        this.loginSpotifyClient = loginSpotifyClient;
    }

    @Override
    public List<GenericDTO> findPlaylistsByCategory(String category) {

        String accessToken = getAccessToken();

        final PlaylistResponseDTO playlist = this.categorieSpotityClient.findPlaylistsByCategory(accessToken, category);

        return convertPlaylistsToDTO(playlist.getPlaylists().getItems());
    }

    @Override
    public List<GenericDTO> findTracksByPlaylistId(String playlistId) {

        String accessToken = getAccessToken();

        final PlaylistTrackResponseDTO playlist = this.playlistSpotifyClient.findPlaylistsByCategory(accessToken, playlistId);

        return convertTracksToDTO(playlist.getItems());
    }

    private String getAccessToken(){
        final LoginResponseDTO loginResponse = this.loginSpotifyClient.autenticar(new LoginRequestDTO("client_credentials"));
        return "Bearer ".concat(loginResponse.getAccess_token());
    }

    private List<GenericDTO> convertPlaylistsToDTO(List<GenericResponseDTO> playlists){
        return playlists.stream()
                .map(playlistResponseDTO -> GenericDTO.builder()
                        .id(playlistResponseDTO.getId())
                        .name(playlistResponseDTO.getName())
                        .build())
                .collect(Collectors.toList());
    }

    private List<GenericDTO> convertTracksToDTO(List<TrackResponseDTO> playlists){
        return playlists.stream()
                .map(playlistResponseDTO -> GenericDTO.builder()
                        .id(playlistResponseDTO.getTrack().getId())
                        .name(playlistResponseDTO.getTrack().getName())
                        .build())
                .collect(Collectors.toList());
    }
}
