/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.bniwebservice.helper;

import org.springframework.stereotype.Service;

/**
 *
 * @author HISBIKAL
 */
@Service
public class ValidPassword {
    
    String chars = ".*[0-9].*";
    String numbers = ".*[A-Za-z].*";
    
    public boolean isValid(String password){
        return password.matches(chars) && password.matches(numbers);
    }
}
