package br.com.ifood.climateservice.infrastructure.rest.client;

import br.com.ifood.climateservice.framework.configuration.FeignEncoderConfig;
import br.com.ifood.climateservice.infrastructure.rest.client.dto.WeatherDTO;
import br.com.ifood.securitylib.exceptions.NotFoundException;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weatherMapClient", url = "http://api.openweathermap.org/data/2.5/weather",
                fallbackFactory = WeatherMapClientFallback.class, configuration = FeignEncoderConfig.class)
public interface WeatherMapClient {

    @GetMapping
    WeatherDTO getWeather(@RequestParam String id,
                          @RequestParam(name = "APPID") String appid,
                          @RequestParam String units,
                          @RequestParam(required = false) String q,
                          @RequestParam(required = false) String lat,
                          @RequestParam(required = false) String lon);
}

@Component
class WeatherMapClientFallback implements FallbackFactory<WeatherMapClient>{

    @Override
    public WeatherMapClient create(Throwable cause) {

        return (id, appid, units, q, lat, lon) -> {
            if( cause instanceof NotFoundException ){
                WeatherDTO weatherDTO = new WeatherDTO(false);
                weatherDTO.setMessage(cause.getMessage());
                return weatherDTO;
            }else{
                return new WeatherDTO(true);
            }
        };

    }
}
