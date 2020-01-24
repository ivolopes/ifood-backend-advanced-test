package br.com.ifood.climateservice.framework.exceptions;

public class ServiceUnavailableException extends RuntimeException {

    public ServiceUnavailableException(String message){
        super(message);
    }
}
