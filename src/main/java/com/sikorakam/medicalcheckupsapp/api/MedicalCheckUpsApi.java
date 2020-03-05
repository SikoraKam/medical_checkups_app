package com.sikorakam.medicalcheckupsapp.api;

import com.sikorakam.medicalcheckupsapp.dao.entity.CheckUp;
import com.sikorakam.medicalcheckupsapp.manager.CheckUpsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/checkups")
public class MedicalCheckUpsApi {
    private CheckUpsManager checkUps;

    @Autowired
    public MedicalCheckUpsApi(CheckUpsManager checkUps) {
        this.checkUps = checkUps;
    }

    @GetMapping("/all")
    public Iterable<CheckUp> getAll(){return checkUps.findAll();}
    @GetMapping
    public Optional<CheckUp> getById(@RequestParam Long index){
        return checkUps.findById(index);
    }
    @PostMapping
    public CheckUp addCheckUp(@RequestBody CheckUp checkUp){
        return checkUps.save(checkUp);
    }
    @PutMapping
    public CheckUp updateCheckUp(@RequestBody CheckUp checkUp){
        return checkUps.save(checkUp);
    }
    @DeleteMapping
    public void deleteCheckUp(@RequestParam Long index){
        checkUps.deleteById(index);
    }
}
