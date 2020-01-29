package br.com.ifood.climateservice.framework.configuration;

import br.com.ifood.securitylib.exceptions.BadRequestException;
import br.com.ifood.securitylib.exceptions.ForbiddenException;
import br.com.ifood.securitylib.exceptions.NotFoundException;
import br.com.ifood.securitylib.exceptions.UnauthorizedException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 401 ) {
            return new UnauthorizedException(response.reason());
        }else if (response.status() == 403 ) {
            return new ForbiddenException(response.reason());
        }else if (response.status() == 404 ) {
            return new NotFoundException("City not found");
        }else if (response.status() >= 400 && response.status() <= 499 ) {
            return new BadRequestException(response.reason());
        }else if(response.status() >= 500 && response.status() <= 599) {
            return new Exception(response.reason());
        } else {
            return FeignException.errorStatus(methodKey, response);
        }
    }

}
