/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.bniwebservice.controller;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import mii.bniwebservice.helper.RandomPassword;
import mii.bniwebservice.helper.ValidPassword;
import mii.bniwebservice.response.ResponseApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mii.bniwebservice.request.LoginRequest;
import org.springframework.web.bind.annotation.RequestBody;
import mii.bniwebservice.model.Users;
import mii.bniwebservice.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import mii.bniwebservice.request.ResetPasword;
import mii.bniwebservice.request.UserRequest;
import mii.bniwebservice.response.NewUserResponse;
import org.springframework.http.HttpStatus;
import mii.bniwebservice.model.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import mii.bniwebservice.response.LoginResponse;
import mii.bniwebservice.request.SaveUserRequest;
import mii.bniwebservice.response.ResponseMessage;
import org.springframework.security.core.context.SecurityContextHolder;
/**
 *
 * @author HISBIKAL
 */
@RestController
@RequestMapping("api/auth")
public class AuthenticationController {
    
    @Autowired 
    private UserService service;
    
    @Autowired
    RandomPassword randomPass;
    
    @Autowired
    ValidPassword validPassword;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    private String password;
        
    @GetMapping("")
    public ResponseEntity<String> home(){
        return ResponseApi.ok("Please sign in");
    }
        
    @PostMapping("login")
    public ResponseApi<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        String responseData = service.login(request);
                
        try{
            Users user = service.findByEmail(request.getEmail());
            LoginResponse loginResponse = new LoginResponse(user.getId(), user.getEmail());
            if (responseData.equalsIgnoreCase("success")) {
                return ResponseApi.apiOk(loginResponse, "success");
            } else {
                return ResponseApi.apiFailed("Maaf password tidak cocok", HttpStatus.UNAUTHORIZED);
            }
        }
        catch(Exception ex){
            return ResponseApi.apiFailed("Maaf user belum melakukan registrasi", HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("change-password")
    public ResponseApi<String> changePasssword (@Valid @RequestBody ResetPasword req){
        
        Users user = service.findByEmail(req.getEmail());
        
        try{
            if(user != null){
                
                if(validPassword.isValid(req.getPasswordBaru())){
                    if (passwordEncoder.matches(req.getPasswordLama(), user.getPassword())){

                        if(req.getPasswordBaru().equals(req.getKonfirmasiPassword())){

                            service.updatePassword(passwordEncoder.encode(req.getPasswordBaru()), req.getEmail());
                            return ResponseApi.apiOk("success");
                        }
                        else{
                            return ResponseApi.apiOk("Password baru dan konfirmasi password tidak cocok");
                        }

                    }else{
                        return ResponseApi.apiOk("Maaf password tidak cocok");
                    }
                }
                else{
                    return ResponseApi.apiOk("Password baru harus mengandung huruf dan angka ");
                }
            }
            else{
                return ResponseApi.apiOk("Maaf user belum melakukan registrasi");
            }
        }
        catch(Exception ex){
            return ResponseApi.apiFailed("error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("register")
    public ResponseApi<NewUserResponse> register(@Valid @RequestBody SaveUserRequest newUser){
        Users checkEmail = service.findByEmail(newUser.getEmail());
        
        this.password = randomPass.randomString(10);
        if(service.findByKtp(newUser.getNomorKtp()) != null){
            return ResponseApi.apiFailed("Maaf NIK sudah digunakan",HttpStatus.BAD_REQUEST);
        }
        else{
            if(checkEmail == null){
                try{
                    service.save(newUser,this.password);
                    Optional<Users> res = service.getById(service.lastData().getId());
                    NewUserResponse responseAdd = new NewUserResponse(res.get().getId(),this.password);
                    return ResponseApi.apiOk(responseAdd,"success");
                }
                catch(Exception ex){
                    return ResponseApi.apiFailed("error",HttpStatus.BAD_REQUEST);
                }
            }
            else{
                return ResponseApi.apiFailed("Maaf email sudah digunakan",HttpStatus.OK);
            }
        }
    }
    
    @PostMapping("logout")
    public ResponseApi<String> logout(HttpServletRequest request) {

        SecurityContextHolder.getContext().setAuthentication(null);

        return ResponseApi.apiOk("success");
    }
}
