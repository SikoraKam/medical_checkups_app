package com.sikorakam.medicalcheckupsapp.api;


import com.sikorakam.medicalcheckupsapp.dao.ClientRepository;
import com.sikorakam.medicalcheckupsapp.dao.entity.Client;
import com.sikorakam.medicalcheckupsapp.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/clients")
    public Iterable<Client> findAllClients() {
        return clientRepository.findAll();
    }

    @PostMapping("/clients")
    public Client createClient(@RequestBody Client client){
        return clientRepository.save(client);
    }

    @PutMapping("/clients/{clientId}")
    public Client updateClient(@PathVariable Long clientId, @Valid @RequestBody Client clientNew) throws NotFoundException {
        return clientRepository.findById(clientId).map(client -> {
            client.setName(clientNew.getName());
            client.setLastname(clientNew.getLastname());
            return clientRepository.save(client);
        }).orElseThrow(()->new NotFoundException("client not found"));
    }

    @DeleteMapping("/clients/{clientId}")
    public String deleteClient(@PathVariable (value = "clientId") Long id) throws NotFoundException {
        return clientRepository.findById(id).map(client -> {
            clientRepository.delete(client);
            return "client deleted";
        }).orElseThrow(()->new NotFoundException("client not found"));
    }

}
