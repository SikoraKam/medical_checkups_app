//package com.sikorakam.medicalcheckupsapp.dao.service;
//
//import com.sikorakam.medicalcheckupsapp.dao.ClientRepository;
//import com.sikorakam.medicalcheckupsapp.dao.RoleRepository;
//import com.sikorakam.medicalcheckupsapp.dao.entity.Client;
//import com.sikorakam.medicalcheckupsapp.dao.entity.Role;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//import java.util.HashSet;
//
//@Service
//public class ClientServiceImp implements ClientService {
//
//    @Autowired
//    BCryptPasswordEncoder encoder;
//    @Autowired
//    RoleRepository roleRepository;
//    @Autowired
//    ClientRepository clientRepository;
//
//    @Override
//    public void saveClient(Client client){
//        client.setPassword(encoder.encode(client.getPassword()));
//        client.setStatus("Verfied");
//        Role clientRole = roleRepository.findByRole("CLIENT"); //diffrent role
//        client.setRoles(new HashSet<Role>(Arrays.asList(clientRole)));
//
//        clientRepository.save(client);
//    }
//
//    @Override
//    public boolean isClientAlreadyPresent(Client client) {
//        //TODO:to implement
//        return false;
//    }
//
//
//}
