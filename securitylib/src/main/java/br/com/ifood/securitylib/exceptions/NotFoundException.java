package br.com.ifood.securitylib.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message){
        super(message);
    }
}
