package br.com.ifood.apigateway.frameworks.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message){
        super(message);
    }
}
