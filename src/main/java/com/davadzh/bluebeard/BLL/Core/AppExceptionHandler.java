package com.davadzh.bluebeard.BLL.Core;

import com.davadzh.bluebeard.BLL.Exceptions.BadRequestException;
import com.davadzh.bluebeard.BLL.Exceptions.NotFoundException;
import com.davadzh.bluebeard.BLL.Exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.function.UnaryOperator;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> NotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(new Response(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Response> BadRequestException(BadRequestException ex) {
        return new ResponseEntity<>(new Response(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Response> UnauthorizedException(UnauthorizedException ex) {
        return new ResponseEntity<>(new Response(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}
