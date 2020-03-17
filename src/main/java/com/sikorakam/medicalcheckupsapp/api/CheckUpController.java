package com.sikorakam.medicalcheckupsapp.api;

import com.sikorakam.medicalcheckupsapp.dao.CheckUpsRepo;
import com.sikorakam.medicalcheckupsapp.dao.ClientRepository;
import com.sikorakam.medicalcheckupsapp.dao.entity.CheckUp;
import com.sikorakam.medicalcheckupsapp.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;




@Service
@RestController
@RequestMapping("/api")
public class CheckUpController {

    @Autowired
    private CheckUpsRepo checkUpsRepo;

    @Autowired
    ClientRepository clientRepository;


    @GetMapping("/checkups")
    public Iterable<CheckUp> findAllCheckUps(){
        return checkUpsRepo.findAll();
    }

    @GetMapping("/checkups/{id}")
    public Optional<CheckUp> findCheckUpById(@PathVariable Long id){
        return checkUpsRepo.findById(id);
    }

    @GetMapping("/clients/{clientId}/checkups")
    public Iterable<CheckUp> findAllCheckUpsByClientId(@PathVariable (value = "clientId") Long clientId){
        return checkUpsRepo.findByClientId(clientId);
    }

    @PostMapping("/clients/{clientId}/checkups")
    public CheckUp addCheckUp(@PathVariable (value = "clientId") Long clientId, @Valid @RequestBody CheckUp checkUp) throws NotFoundException {
        return clientRepository.findById(clientId).map(client -> {
            checkUp.setClient(client);
            return checkUpsRepo.save(checkUp);
        }).orElseThrow(() -> new NotFoundException("Client not found"));
    }

    @PutMapping("/checkups/{id}")
    public CheckUp updateCheckUp(@PathVariable Long id, @Valid @RequestBody CheckUp checkUpNew) throws NotFoundException {
        return checkUpsRepo.findById(id)
                .map(checkUp -> {
                    checkUp.setDate(checkUpNew.getDate());
                    return checkUpsRepo.save(checkUp);
                }).orElseThrow(()->new NotFoundException("check-up with this id not found"));
    }

    @DeleteMapping("/checkups/{id}")
    public String deleteCheckUp(@PathVariable (value = "id") Long id) throws NotFoundException {
        checkUpsRepo.deleteById(id);
        return "deleted";
    }


}
