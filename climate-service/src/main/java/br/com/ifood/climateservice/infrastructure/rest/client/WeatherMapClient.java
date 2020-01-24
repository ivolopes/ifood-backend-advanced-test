package br.com.ifood.climateservice.infrastructure.rest.client;

import br.com.ifood.climateservice.infrastructure.rest.client.dto.WeatherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weatherMapClient", url = "http://api.openweathermap.org/data/2.5/weather",
                fallback = WeatherMapClientFallback.class)
public interface WeatherMapClient {

    @GetMapping
    WeatherDTO getWeather(@RequestParam String id,
                          @RequestParam(name = "APPID") String appid,
                          @RequestParam String units,
                          @RequestParam(required = false) String q,
                          @RequestParam(required = false) String lat,
                          @RequestParam(required = false) String lon);
}

class WeatherMapClientFallback implements  WeatherMapClient{

    @Override
    public WeatherDTO getWeather(String id, String appid, String units, String q, String lat, String lon) {
        return new WeatherDTO(true);
    }
}
