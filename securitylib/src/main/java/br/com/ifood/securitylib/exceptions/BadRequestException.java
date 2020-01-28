package br.com.ifood.securitylib.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message){
        super(message);
    }
}
