package com.sikorakam.medicalcheckupsapp.security;

import com.sikorakam.medicalcheckupsapp.dao.ClientRepository;
import com.sikorakam.medicalcheckupsapp.dao.entity.Client;
import com.sikorakam.medicalcheckupsapp.security.ClientDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientDetailsServiceImpl implements UserDetailsService {

    private ClientRepository clientRepository;

    public ClientDetailsServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Client client = this.clientRepository.findByName(s);
        return ClientDetailsImpl.build(client);
    }

}
