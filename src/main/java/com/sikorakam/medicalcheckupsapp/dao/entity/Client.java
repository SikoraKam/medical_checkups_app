package com.sikorakam.medicalcheckupsapp.dao.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "client")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Client {
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastname;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CheckUp> checkUps;

    public Client() {
    }

    public Client(String name, String lastname, Set<CheckUp> checkUps) {
        this.name = name;
        this.lastname = lastname;
        this.checkUps = checkUps;
    }

    public Client(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Set<CheckUp> getCheckUps() {
        return checkUps;
    }

    public void setCheckUps(Set<CheckUp> checkUps) {
        this.checkUps = checkUps;
    }
}
