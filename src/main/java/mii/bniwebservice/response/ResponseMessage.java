/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.bniwebservice.response;;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author Hisbikal-Haqqi
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMessage<E> extends ResponseData<E>{
    
    private String message;

    public ResponseMessage(E data, String message) {
        super(data);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
