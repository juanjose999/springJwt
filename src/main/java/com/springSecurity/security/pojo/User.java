package com.springSecurity.security.pojo;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.print.attribute.standard.MediaSize;
@NamedQuery(name = "User.findByEmail",query = "select u from User u where u.email=:email")
@Entity
@Data
@DynamicUpdate
@DynamicInsert
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column(name = "nombre")
    private  String nombre;

    @Column(name = "numeroContacto")
    private String numeroDeContacto;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private String status;

    @Column(name = "role")
    private String role;


}
