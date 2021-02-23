/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.bniwebservice.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import mii.bniwebservice.helper.RandomPassword;
import mii.bniwebservice.response.ResponseApi;
import mii.bniwebservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mii.bniwebservice.model.Users;
import mii.bniwebservice.model.UsersData;
import mii.bniwebservice.request.UserRequest;
import mii.bniwebservice.response.NewUserResponse;
import org.mindrot.jbcrypt.BCrypt;
import mii.bniwebservice.request.SaveUserRequest;

/**
 *
 * @author HISBIKAL
 */
@RestController
@RequestMapping("api/user")
public class UserController {
    
    @Autowired
    private UserService service;
    
    @Autowired
    RandomPassword randomPass;
        
    @GetMapping("")
    public ResponseApi<List<Users>> findAll(){
        if(service.read().size() == 0){
             return ResponseApi.apiOk("Maaf data tidak tersedia");
        }
        else{
            return ResponseApi.apiOk(service.read(), "success");
        }
    }
    
    @GetMapping("{id}")
    public ResponseApi<Optional<Users>> getById(@PathVariable("id")Integer id){
        Optional<Users> findUser = service.getById(id);
        
        if(!findUser.isPresent()){
            return ResponseApi.apiFailed("Maaf data tidak ditemukan",HttpStatus.BAD_REQUEST);
        }
        else{
            return ResponseApi.apiOk(findUser, "success");
        }
    }
           
    @PutMapping("")
    public ResponseApi<Optional<UsersData>> updateUser(@RequestBody SaveUserRequest newUser){
        
        try{
            service.save(newUser,"");
            return ResponseApi.apiOk("success");
        }
        catch(Exception e){
            return ResponseApi.apiFailed("Maaf nik tidak bisa diperbarui",HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("{id}")
    public ResponseApi deleteUser(@PathVariable("id") Integer id){
        
        try{
            service.delete(id);
            return ResponseApi.apiOk("success");
        }
        catch(Exception e){
             return ResponseApi.apiFailed("Maaf id user tidak diemukan",HttpStatus.BAD_REQUEST);
        }
    }
}
