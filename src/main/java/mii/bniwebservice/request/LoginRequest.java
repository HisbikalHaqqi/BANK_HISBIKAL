/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.bniwebservice.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 *
 * @author HISBIKAL
 */
@Data
public class LoginRequest {
    
    @NotBlank(message = "Email harus diisi")
    @Email(message = "Email tidak valid")
    private String email;
    
    @NotBlank(message = "Password harus diisi")
    private String password;
}
