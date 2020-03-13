package com.sikorakam.medicalcheckupsapp.api;

import com.sikorakam.medicalcheckupsapp.dao.Category;
import com.sikorakam.medicalcheckupsapp.dao.CheckUpsRepo;
import com.sikorakam.medicalcheckupsapp.dao.ClientRepository;
import com.sikorakam.medicalcheckupsapp.dao.entity.CheckUp;
import com.sikorakam.medicalcheckupsapp.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

import static com.sikorakam.medicalcheckupsapp.dao.Category.KREW;

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
        }).orElseThrow(()-> new NotFoundException("Client not found"));
    }

    @PutMapping("/checkups/{id}")
    public CheckUp updateCheckUp(@PathVariable Long id, @Valid @RequestBody CheckUp checkUpNew) throws NotFoundException {
        return checkUpsRepo.findById(id)
                .map(checkUp -> {
                    checkUp.setCategory(checkUpNew.getCategory());
                    checkUp.setDate(checkUpNew.getDate());
                    return checkUpsRepo.save(checkUp);
                }).orElseThrow(()->new NotFoundException("check-up with this id not found"));
    }


    @DeleteMapping("/checkups/{id}")
    public String deleteCheckUp(@PathVariable Long id) throws NotFoundException {
        return checkUpsRepo.findById(id).map(checkUp -> {
            checkUpsRepo.delete(checkUp);
            return "Deleted";
        }).orElseThrow(()->new NotFoundException("checkup not found"));
    }

    /*
    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
        Category categ = KREW;
        checkUpsRepo.save(new CheckUp(KREW, LocalDate.of(2018,10,01)));

    }*/


}
