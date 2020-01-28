package br.com.ifood.securitylib.exceptions;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException(String message){
        super(message);
    }
}
