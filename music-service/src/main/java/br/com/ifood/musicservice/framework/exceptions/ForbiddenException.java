package br.com.ifood.musicservice.framework.exceptions;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException(String message){
        super(message);
    }
}
