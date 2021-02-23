/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.bniwebservice.repositories;

import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import mii.bniwebservice.model.Users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author HISBIKAL
 */
public interface UsersRepositories extends JpaRepository<Users, Integer>{
        
    Users findByEmail(@Param("email") String email);
    
    @Modifying
    @Transactional
    @Query(value="update users set password = ?1 where email = ?2",nativeQuery = true)
    void updatePassword(String password,String username);
        
    @Query(value = "SELECT * FROM users ORDER by id DESC limit 1",nativeQuery = true)
    Users lastData();
}
