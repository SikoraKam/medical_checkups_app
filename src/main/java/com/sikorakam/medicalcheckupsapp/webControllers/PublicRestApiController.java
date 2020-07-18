package com.sikorakam.medicalcheckupsapp.webControllers;


import com.sikorakam.medicalcheckupsapp.dao.ClientRepository;
import com.sikorakam.medicalcheckupsapp.dao.entity.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/public")
public class PublicRestApiController {

    private ClientRepository clientRepository;



    public PublicRestApiController(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @GetMapping("test1")
    public String test1(){
        return "API Test 1";
    }

    @GetMapping("test2")
    public String test2(){
        return "API Test 2";
    }

    @GetMapping("users")
    public List<Client> users(){
        return this.clientRepository.findAll();
    }
}