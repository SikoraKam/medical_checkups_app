package com.sikorakam.medicalcheckupsapp.api;

import com.sikorakam.medicalcheckupsapp.dao.Category;
import com.sikorakam.medicalcheckupsapp.dao.CheckUpsRepo;
import com.sikorakam.medicalcheckupsapp.dao.entity.CheckUp;
import com.sikorakam.medicalcheckupsapp.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
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

    @GetMapping("/checkups")
    public Iterable<CheckUp> findAllCheckUps(){
        return checkUpsRepo.findAll();
    }

    @GetMapping("/checkups/{id}")
    public Optional<CheckUp> findCheckUpById(@PathVariable Long id){
        return checkUpsRepo.findById(id);
    }

    @PostMapping("/checkups")
    public CheckUp createCheckUp(@RequestBody CheckUp checkUp){
        return checkUpsRepo.save(checkUp);
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
    public void deleteCheckUp(@PathVariable Long id){
        checkUpsRepo.deleteById(id);
    }

    /*
    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
        Category categ = KREW;
        checkUpsRepo.save(new CheckUp(KREW, LocalDate.of(2018,10,01)));

    }*/


}
