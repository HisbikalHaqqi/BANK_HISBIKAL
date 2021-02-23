/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.bniwebservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Data;

/**
 *
 * @author HISBIKAL
 */
@Data
@Entity
@Table(name = "users_data")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersData.findAll", query = "SELECT u FROM UsersData u")
    , @NamedQuery(name = "UsersData.findById", query = "SELECT u FROM UsersData u WHERE u.id = :id")
    , @NamedQuery(name = "UsersData.findByNamaLengkap", query = "SELECT u FROM UsersData u WHERE u.namaLengkap = :namaLengkap")
    , @NamedQuery(name = "UsersData.findByAlamat", query = "SELECT u FROM UsersData u WHERE u.alamat = :alamat")
    , @NamedQuery(name = "UsersData.findByTempatLahir", query = "SELECT u FROM UsersData u WHERE u.tempatLahir = :tempatLahir")
    , @NamedQuery(name = "UsersData.findByTanggalLahir", query = "SELECT u FROM UsersData u WHERE u.tanggalLahir = :tanggalLahir")
    , @NamedQuery(name = "UsersData.findByNomorKtp", query = "SELECT u FROM UsersData u WHERE u.nomorKtp = :nomorKtp")
    , @NamedQuery(name = "UsersData.findByNomorHandphone", query = "SELECT u FROM UsersData u WHERE u.nomorHandphone = :nomorHandphone")})
public class UsersData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
        
    @Column(name = "nama_lengkap")
    private String namaLengkap;
        
    @Basic(optional = false)
    @Column(name = "alamat")
    private String alamat;
       
    @Basic(optional = false)
    @Column(name = "tempat_lahir")
    private String tempatLahir;
    
    @Basic(optional = false)
    @Column(name = "tanggal_lahir")
    @Temporal(TemporalType.DATE)
    private Date tanggalLahir;
    
    @Basic(optional = false)
    @Column(name = "nomor_ktp")
    private String nomorKtp;
    
    @Basic(optional = false)
    @Column(name = "nomor_handphone")
    private String nomorHandphone;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersData", fetch = FetchType.EAGER)
    private List<Users> usersList;

    public UsersData() {
    }

    
    @XmlTransient
    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }
}
