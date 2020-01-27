package br.com.ifood.musicservice.framework.exceptions;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message){
        super(message);
    }
}
