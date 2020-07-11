package com.sikorakam.medicalcheckupsapp;

import com.sikorakam.medicalcheckupsapp.dao.entity.Client;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ClientPrincipal implements UserDetails {

    private Client client;

    public ClientPrincipal() {
    }

    public ClientPrincipal(Client client) {
        this.client = client;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();

        this.client.getRoles().forEach(r->{
            String role = r.getRole();
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
            authorityList.add(authority);
        });
        return authorityList;
    }

    @Override
    public String getPassword() {
        return this.client.getPassword();
    }

    @Override
    public String getUsername() {
        return this.client.getName();
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
        return this.client.getActive() == 1;
    }
}
