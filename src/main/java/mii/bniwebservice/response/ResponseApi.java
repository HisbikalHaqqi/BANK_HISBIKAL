/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.bniwebservice.response;

import java.util.Optional;
import mii.bniwebservice.model.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Hisbikal-Haqqi
 */
public class ResponseApi <E> extends ResponseEntity<ResponseMessage<E>>{
    
    public ResponseApi(ResponseMessage<E> data, HttpStatus status) {
        super(data, status);
    }
    
    public static <E> ResponseApi <E> apiOk(E data, String message) {
        return new ResponseApi<>(new ResponseMessage(data, message), HttpStatus.OK);
    }
    
    public static <E> ResponseApi <E> apiFailed(String message, HttpStatus status) {
        return new ResponseApi<>(new ResponseMessage(null, message), status);
    }
    
    public static <E> ResponseApi <E> apiOk(E data) {
        return apiOk(data, null);
    }
    
    public static <E> ResponseApi <E> apiOk(String message) {
        return apiOk(null, message);
    }
}
