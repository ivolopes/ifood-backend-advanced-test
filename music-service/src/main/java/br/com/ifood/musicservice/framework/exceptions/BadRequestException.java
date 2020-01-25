package br.com.ifood.musicservice.framework.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message){
        super(message);
    }
}
