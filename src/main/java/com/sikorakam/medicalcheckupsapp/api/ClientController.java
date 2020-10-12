package com.sikorakam.medicalcheckupsapp.api;



import com.sikorakam.medicalcheckupsapp.dao.ClientRepository;
import com.sikorakam.medicalcheckupsapp.dao.entity.Client;
import com.sikorakam.medicalcheckupsapp.exception.NotFoundException;
import com.sikorakam.medicalcheckupsapp.security.ClientDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    @GetMapping("/clients")
    public Iterable<Client> findAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/clients/{id}")
    public Optional<Client> findClientById(@PathVariable Long id){
        return clientRepository.findById(id);
    }

//    @PostMapping("/getClient/{clientId}")
//    public String getClient(@PathVariable("clientId") Long id, Model model){
//        model.addAttribute("CLIENT", findClientById(id));
//        return "client";
//    }

    @RequestMapping("/getClientId")
    public Long getClientId(@AuthenticationPrincipal ClientDetailsImpl clientPrincipal){
        return clientPrincipal.getId();
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
            client.setEmail(clientNew.getEmail());
            client.setPassword(encoder.encode(clientNew.getPassword()));
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
