package br.com.ifood.musicservice.infrastructure.rest.controller.v1.track;

import br.com.ifood.musicservice.infrastructure.port.application.TrackApplicationPort;
import br.com.ifood.musicservice.infrastructure.rest.controller.dto.GenericDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/tracks",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE
        })
public class TrackController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private TrackApplicationPort trackApplication;

    @Autowired
    public TrackController(TrackApplicationPort trackApplication){
        this.trackApplication = trackApplication;
    }

    @GetMapping
    public ResponseEntity<List<GenericDTO>> findClimate(@RequestParam(required = false) String lat,
                                                        @RequestParam(required = false) String lon,
                                                        @RequestParam(required = false) String city){

        final List<GenericDTO> tracks = trackApplication.getTracks(city, lat, lon);

        return ResponseEntity.ok(tracks);
    }

}
