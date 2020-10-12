package com.sikorakam.medicalcheckupsapp.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sikorakam.medicalcheckupsapp.dao.entity.Client;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ClientDetailsImpl implements UserDetails {

    private static final Long serialVersionUID = 1L;


    private Long id;
    private String name;
    private String lastname;
    private String email;
    @JsonIgnore
    private String password;


    private Collection<? extends  GrantedAuthority> authorities;

    public ClientDetailsImpl() {
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorityList = new ArrayList<>();
//
//        this.client.getRoles().forEach(r->{
//            String role = r.getRole();
//            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
//            authorityList.add(authority);
//        });

//        return authorityList;
//    }

    public ClientDetailsImpl(Long id, String name, String lastname, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static ClientDetailsImpl build(Client client){
        List<GrantedAuthority> authorities = client.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());

        return new ClientDetailsImpl(
                client.getId(),
                client.getName(),
                client.getLastname(),
                client.getEmail(),
                client.getPassword(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClientDetailsImpl client = (ClientDetailsImpl) o;
        return Objects.equals(id, client.id);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }
}
