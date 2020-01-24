package br.com.ifood.climateservice.infrastructure.rest.controller.v1.climate;

import br.com.ifood.climateservice.infrastructure.port.application.ClimateApplicationPort;
import br.com.ifood.climateservice.infrastructure.rest.controller.v1.climate.dto.ClimateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/climates",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE
        })
public class ClimateController {

        private ClimateApplicationPort climateApplication;

        @Autowired
        public ClimateController(ClimateApplicationPort climateApplication){
                this.climateApplication = climateApplication;
        }

        @GetMapping
        public ResponseEntity<ClimateDTO> findClimate(@RequestParam(required = false) String lat,
                                                      @RequestParam(required = false) String lon,
                                                      @RequestParam(required = false) String city){

                ClimateDTO climateDTO = climateApplication.getClimate(lat, lon, city);

                return ResponseEntity.ok(climateDTO);
        }

}
