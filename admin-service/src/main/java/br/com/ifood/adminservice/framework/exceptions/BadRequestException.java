package br.com.ifood.adminservice.framework.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message){
        super(message);
    }
}
