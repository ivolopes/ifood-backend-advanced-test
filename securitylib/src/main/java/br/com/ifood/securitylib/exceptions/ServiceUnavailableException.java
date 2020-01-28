package br.com.ifood.securitylib.exceptions;

public class ServiceUnavailableException extends RuntimeException {

    public ServiceUnavailableException(String message){
        super(message);
    }
}
