/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.bniwebservice.service;


import java.util.Optional;
import mii.bniwebservice.model.MyUserDetail;
import mii.bniwebservice.repositories.UsersRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import mii.bniwebservice.model.Users;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Hisbikal-Haqqi
 */
@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UsersRepositories repository;
     
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users u = new Users();
        
        u = repository.findByEmail(email);
        
        if(u == null){
            throw new UsernameNotFoundException(email);
        }
        
        return new MyUserDetail(u);
    }
}

