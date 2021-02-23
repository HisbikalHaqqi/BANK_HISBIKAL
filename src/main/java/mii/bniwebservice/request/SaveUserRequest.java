/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.bniwebservice.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 *
 * @author HISBIKAL
 */
@Data
public class SaveUserRequest extends UserRequest{
   
    private Integer id;
}
