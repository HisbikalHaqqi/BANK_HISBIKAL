/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.bniwebservice.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import mii.bniwebservice.helper.RandomPassword;
import mii.bniwebservice.repositories.UsersRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import mii.bniwebservice.model.*;
import mii.bniwebservice.repositories.UsersDataRepositories;
import mii.bniwebservice.request.LoginRequest;
import mii.bniwebservice.request.UserRequest;
import mii.bniwebservice.request.SaveUserRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author HISBIKAL
 */
@Service
public class UserService {
    
    @Autowired
    UsersDataRepositories usersData;
    
    @Autowired
    UsersRepositories userRepo;
    
    @Autowired
    RandomPassword randomPass;
        
    @Autowired
    private AuthenticationManager authManager;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    private static Integer startData;
    
    public List<Users> read(){
        return userRepo.findAll();
    }
    
    public boolean save(SaveUserRequest req,String password){
        if(usersData.findAll().size() == 0){
            startData = 1;
        }
        else{
            this.startData = lastData().getId()+1;
        }
        
        UsersData cst = new UsersData();
        cst.setId(this.startData);
        cst.setNamaLengkap(req.getNamaLengkap());
        cst.setAlamat(req.getAlamat());
        cst.setTempatLahir(req.getTempatLahir());
        cst.setTanggalLahir(req.getTanggalLahir());
        cst.setNomorKtp(req.getNomorKtp());
        cst.setNomorHandphone(req.getNomorHandphone());
                        
        Users newUser = new Users();
        newUser.setId(this.startData);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setEmail(req.getEmail());
        newUser.setUsersData(cst);
        
        try{
            if(req.getId() != null){
                usersData.updateUser(cst.getNamaLengkap(),cst.getAlamat(),cst.getTempatLahir(),
                    cst.getTanggalLahir(),cst.getNomorHandphone(),req.getId());
            }
            else{
                usersData.save(cst);
                userRepo.save(newUser);
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return true;
    }
    
    public void delete(Integer userId){
        userRepo.deleteById(userId);
        usersData.deleteById(userId);
        this.startData = lastData().getId();
    }
    
    public Optional<UsersData> findById(Integer userId){
        return usersData.findById(userId);
    }
    
    public UsersData findByKtp(String noKtp){
        return usersData.findByKtp(noKtp);
    }
    
    public Optional<Users> getById(Integer id){
        return userRepo.findById(id);
    }
       
    public Users findByEmail(String email){
        return userRepo.findByEmail(email);
    }
    
    public void updatePassword(String password,String username){
        userRepo.updatePassword(password, username);
    }
    
    public Users lastData(){
        return userRepo.lastData();
    }
    
    public String login(LoginRequest request) {
        try {
            UsernamePasswordAuthenticationToken authReq
                    = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

            Authentication auth = authManager.authenticate(authReq);
            SecurityContextHolder.getContext().setAuthentication(auth);
            return "success";
        } catch (AuthenticationException e) {
            return "failed";
        }
    }
}