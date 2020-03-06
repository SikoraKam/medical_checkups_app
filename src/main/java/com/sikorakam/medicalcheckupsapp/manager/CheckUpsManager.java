/*package com.sikorakam.medicalcheckupsapp.manager;


import com.sikorakam.medicalcheckupsapp.dao.CheckUpsRepo;
import com.sikorakam.medicalcheckupsapp.dao.entity.CheckUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CheckUpsManager {

    private CheckUpsRepo checkUpsRepo;

    @Autowired
    public CheckUpsManager(CheckUpsRepo checkUpsRepo){
        this.checkUpsRepo = checkUpsRepo;
    }

    public Optional<CheckUp> findById(Long id){
        return checkUpsRepo.findById(id);
    }

    public Iterable<CheckUp> findAll(){
        return checkUpsRepo.findAll();
    }
    public CheckUp save(CheckUp checkUp){
        return checkUpsRepo.save(checkUp);
    }
    public void deleteById(Long id){
        checkUpsRepo.deleteById(id);
    }

    /*
    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
        save(new CheckUp("Badanie krwi", LocalDate.of(2018,10,01),52.9));
        save(new CheckUp("Badanie krwi", LocalDate.of(2017,9,11),51.2));

    }
}*/
