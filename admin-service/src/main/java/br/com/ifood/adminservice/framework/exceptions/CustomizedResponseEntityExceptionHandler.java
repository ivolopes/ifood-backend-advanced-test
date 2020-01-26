package br.com.ifood.adminservice.framework.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.Date;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDetails> handleUserBadRequestException(BadRequestException ex, WebRequest request) {
        logger.error(ex.getMessage());
        logger.error(Arrays.toString(ex.getStackTrace()));
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleUserException(Exception ex, WebRequest request) {
        logger.error(ex.getMessage());
        logger.error(Arrays.toString(ex.getStackTrace()));
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
