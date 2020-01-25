package br.com.ifood.musicservice.framework.configuration;

import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.form.FormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignEncoderConfig {

    @Bean
    public Encoder encoder(){
        return new FormEncoder();
    }

    @Bean
    public ErrorDecoder errorDecoder(){
        return new FeignErrorDecoder();
    }

}
