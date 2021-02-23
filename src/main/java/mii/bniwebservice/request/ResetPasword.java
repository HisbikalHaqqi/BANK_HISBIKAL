/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.bniwebservice.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author HISBIKAL
 */
@Data
public class ResetPasword {
    
    @NotBlank(message = "Email harus diisi")
    @Email(message = "Email tidak valid")
    private String email;
    
    @NotBlank(message = "Password lama harus diisi")
    private String passwordLama;
    
    @NotBlank(message = "Password baru harus diisi")
    @Size(min = 8,max = 12,message = "Password baru min 8 dan maks 12 karakter")
    private String passwordBaru;
    
    @NotBlank(message = "Konfirmasi password harus diisi")
    private String konfirmasiPassword;
}
