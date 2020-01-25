package br.com.ifood.musicservice.framework.exceptions;

public class ServiceUnavailableException extends RuntimeException {

    public ServiceUnavailableException(String message){
        super(message);
    }
}
