/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.bniwebservice.exception;

import java.util.Date;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import mii.bniwebservice.exception.ResourceNotFound;
import mii.bniwebservice.response.ResponseApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author HISBIKAL
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    
    //handling specific exception
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<?> resourceNotFoundHandling(ResourceNotFound exception,WebRequest request){
        return ResponseApi.apiFailed(exception.getMessage(), HttpStatus.NOT_FOUND);
    }  
    
    //handling global exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandling(Exception exception,WebRequest request){
       return ResponseApi.apiFailed(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    //handling custom validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> customValidationErrorHandling(MethodArgumentNotValidException exception){
        return ResponseApi.apiFailed(exception.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
