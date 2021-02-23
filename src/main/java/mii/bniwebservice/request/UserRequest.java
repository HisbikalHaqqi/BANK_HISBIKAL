/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.bniwebservice.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author HISBIKAL
 */
@Data
public class UserRequest {
    
    @NotBlank(message = "Nama lengkap harus diisi")
    @Basic(optional = false)
    private String namaLengkap;
    
    @NotBlank(message = "Alamat harus diisi")
    @Size(max = 100,message = "Alamat maximal 100 karakter")
    private String alamat;
    
    @NotBlank(message = "Tempat lahir harus diisi")
    @Size(max = 50,message = "Tempat lahir maximal 50 karakter")
    private String tempatLahir;
    
    @NotNull(message = "Tanggal lahir harus diisi")
    private Date tanggalLahir;
    
    @NotBlank(message = "NIK harus diisi")
    @Size(max = 16,message = "NIK maksimal 16 karakter")
    @Pattern(regexp = "[0-9]+", message = "NIK harus angka")
    private String nomorKtp;
    
    @NotBlank(message = "No handphone harus diisi")
    @Size(max = 12,message = "No handphone maximal 12 karakter")
    @Pattern(regexp = "[0-9]+", message = "No handphone harus angka")
    private String nomorHandphone;
        
    @NotBlank(message = "Email harus diisi")
    @Email(message = "Email tidak valid")
    @Size(max = 50,message = "Email maksimal 50 karakter")
    private String email;
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String password;
}
