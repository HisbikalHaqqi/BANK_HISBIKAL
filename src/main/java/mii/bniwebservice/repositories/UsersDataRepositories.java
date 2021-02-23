/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.bniwebservice.repositories;

import java.util.Date;
import javax.transaction.Transactional;
import mii.bniwebservice.model.UsersData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author HISBIKAL
 */
public interface UsersDataRepositories extends JpaRepository<UsersData, Integer> {
    @Query(value="select * from users_data n where n.nomor_ktp = :noKtp",nativeQuery = true)
    UsersData findByKtp(@Param("noKtp") String noKtp);
    
    @Modifying
    @Transactional
    @Query(value="update users_data set nama_lengkap = ?1, alamat = ?2, tempat_lahir = ?3, "
            + " tanggal_lahir = ?4, nomor_handphone = ?5 where id = ?6",nativeQuery = true)
    void updateUser(String nmLengkap,String alamat,String tempatLahir, Date
            tglLahir,String hp,Integer id);
}
