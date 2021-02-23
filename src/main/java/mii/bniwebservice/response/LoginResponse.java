/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.bniwebservice.response;

import lombok.Data;

/**
 *
 * @author HISBIKAL
 */
@Data
public class LoginResponse {
    
    private int id;
    private String email;

    public LoginResponse(int id, String email) {
        this.id = id;
        this.email = email;
    }
}
