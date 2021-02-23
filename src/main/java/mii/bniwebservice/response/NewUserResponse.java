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
public class NewUserResponse {
 
    private Integer id;
    private String password;

    public NewUserResponse(Integer id, String password) {
        this.id = id;
        this.password = password;
    }
}
