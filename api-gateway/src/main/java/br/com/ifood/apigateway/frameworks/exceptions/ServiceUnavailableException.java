package br.com.ifood.apigateway.frameworks.exceptions;

public class ServiceUnavailableException extends RuntimeException {

    public ServiceUnavailableException(String message){
        super(message);
    }
}
