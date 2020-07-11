package com.sikorakam.medicalcheckupsapp.dao.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "auth_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "auth_role_id")
    private Long id;

    @Column(name = "role_name")
    private String role;

    @Column(name = "role_desc")
    private String desc;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
