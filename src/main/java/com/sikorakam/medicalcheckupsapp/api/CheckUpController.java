package com.sikorakam.medicalcheckupsapp.api;

import com.sikorakam.medicalcheckupsapp.dao.Category;
import com.sikorakam.medicalcheckupsapp.dao.CheckUpsRepo;
import com.sikorakam.medicalcheckupsapp.dao.ClientRepository;
import com.sikorakam.medicalcheckupsapp.dao.TestRepository;
import com.sikorakam.medicalcheckupsapp.dao.entity.CheckUp;
import com.sikorakam.medicalcheckupsapp.dao.entity.Test;
import com.sikorakam.medicalcheckupsapp.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PreRemove;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static com.sikorakam.medicalcheckupsapp.dao.Category.KREW;

@Service
@RestController
@RequestMapping("/api")
public class CheckUpController {

    @Autowired
    private CheckUpsRepo checkUpsRepo;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    TestRepository testRepository;


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
/*
    @PostMapping("/clients/{clientId}/{testId}/checkups")
    public CheckUp addCheckUp(@PathVariable (value = "clientId") Long clientId, @PathVariable (value = "testId") Long testId, @Valid @RequestBody CheckUp checkUp) throws NotFoundException {
        return clientRepository.findById(clientId).map(client -> {
            checkUp.setClient(client);
            try {
                testRepository.findById(testId).map(test -> {
                    Set<Test> tests;
                    tests.add(test);
                    checkUp.setTests(tests);
                return checkUpsRepo.save(checkUp);}).orElseThrow(()-> new NotFoundException("test not found"));
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
            return checkUpsRepo.save(checkUp);
        }).orElseThrow(()-> new NotFoundException("Client not found"));
    }*/

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
/*
    @PutMapping("checkups/{checkupId}/{testId}")
    public CheckUp setTestToCheckUp(@PathVariable (value = "checkupId") Long checkupId, @PathVariable (value = "testId") Long testId) throws NotFoundException {

        Optional<Test> optionalTest = testRepository.findById(testId);
        Set<Test> tests = optionalTest.map(Collections::singleton).orElse(Collections.emptySet());
        return checkUpsRepo.findById(checkupId).map(checkUp -> {
            checkUp.setTests(tests);
            return checkUpsRepo.save(checkUp);
        }).orElseThrow(()->new NotFoundException("checkup not found"));
    }*/


    @DeleteMapping("/checkups/{id}")
    public String deleteCheckUp(@PathVariable (value = "id") Long id) throws NotFoundException {
        checkUpsRepo.deleteById(id);
        return "deleted";
    }

    @PutMapping("checkups/{checkupId}/{testId}")
    public CheckUp setTestToCheckUp(@PathVariable (value = "checkupId") Long checkupId, @PathVariable (value = "testId") Long testId) throws NotFoundException {

        Optional<Test> optionalTest = testRepository.findById(testId);
        Optional<CheckUp> optionalCheckUp = checkUpsRepo.findById(checkupId);
        optionalCheckUp.get().getTests().add(optionalTest.get());
        return checkUpsRepo.save(optionalCheckUp.get());
    }
}
