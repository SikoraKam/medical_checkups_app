package com.sikorakam.medicalcheckupsapp;

import com.sikorakam.medicalcheckupsapp.dao.ClientRepository;
import com.sikorakam.medicalcheckupsapp.dao.entity.Client;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientPrincipalDetailService implements UserDetailsService {

    private ClientRepository clientRepository;

    public ClientPrincipalDetailService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Client client = this.clientRepository.findByName(s);
        ClientPrincipal clientPrincipal = new ClientPrincipal(client);
        return clientPrincipal;
    }

}
