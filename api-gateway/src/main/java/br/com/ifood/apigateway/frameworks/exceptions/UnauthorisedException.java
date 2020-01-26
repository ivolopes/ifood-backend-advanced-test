package br.com.ifood.apigateway.frameworks.exceptions;

public class UnauthorisedException extends RuntimeException {

    public UnauthorisedException(String message){
        super(message);
    }
}
