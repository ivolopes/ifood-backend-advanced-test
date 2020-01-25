package br.com.ifood.musicservice.framework.configuration;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;

public class SpotifyClientConfiguration {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("b1df09a2a44e4bf4b355ac2e8db0e2fc", "9361ec62e3bd4024a3736bb26af88345");
    }

}
