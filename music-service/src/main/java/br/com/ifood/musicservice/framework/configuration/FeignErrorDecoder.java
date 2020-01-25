package br.com.ifood.musicservice.framework.configuration;

import br.com.ifood.musicservice.framework.exceptions.BadRequestException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() >= 400 && response.status() <= 499 ) {
            return new BadRequestException(response.reason());
        }else if(response.status() >= 500 && response.status() <= 599) {
            return new Exception(response.reason());
        } else {
            return FeignException.errorStatus(methodKey, response);
        }
    }

}
