package br.com.ifood.adminservice.framework.exceptions;

public class UnauthorisedException extends RuntimeException {

    public UnauthorisedException(String message){
        super(message);
    }
}
