package com.sikorakam.medicalcheckupsapp.dao.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "client",
        uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Client {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;
    @Column(name = "name")

    @NotNull(message="First name is obligatory")
    private String name;

    @NotNull(message="Last name is obligatory")
    @Column(name = "lastname")
    private String lastname;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CheckUp> checkUps;


    //---------------------security fields-------------------
    @NotNull(message="Email is obligatory")
    @Email(message = "Email is invalid")
    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private String status;


    @NotNull(message="Password is obligatory")
    @Length(min=5, message="Password should be at least 5 characters")
    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "auth_user_role", joinColumns = @JoinColumn(name = "auth_user_id"), inverseJoinColumns = @JoinColumn(name = "auth_role_id"))
    private Set<Role> roles;

    //---------------------------------------------------------------


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

    public Client(@NotNull(message = "First name is obligatory") String name, @NotNull(message = "Last name is obligatory") String lastname, @NotNull(message = "Email is obligatory") @Email(message = "Email is invalid") String email, @NotNull(message = "Password is obligatory") @Length(min = 5, message = "Password should be at least 5 characters") String password, Set<Role> roles) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.roles = roles;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
