/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.bniwebservice.exception;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author HISBIKAL
 */
@Data
public class ErrorDetails {
    
    private String message;

    public ErrorDetails(String message) {
        super();
        this.message = message;
    }
}
