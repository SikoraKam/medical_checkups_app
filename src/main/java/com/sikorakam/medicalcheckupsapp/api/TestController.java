package com.sikorakam.medicalcheckupsapp.api;

import com.sikorakam.medicalcheckupsapp.dao.CheckUpsRepo;
import com.sikorakam.medicalcheckupsapp.dao.TestRepository;
import com.sikorakam.medicalcheckupsapp.dao.entity.Result;
import com.sikorakam.medicalcheckupsapp.dao.entity.Test;
import com.sikorakam.medicalcheckupsapp.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private CheckUpsRepo checkUpsRepo;

    @GetMapping("/tests")
    private List<Test> findAllTests(){
        return testRepository.findAll();
    }

    @GetMapping("/checkups/{checkupId}/tests")
    private List<Test> findAllTestsByCheckUpId(@PathVariable (name = "checkupId") Long checkUpId) throws NotFoundException {
        if(!checkUpsRepo.existsById(checkUpId)){
            throw new NotFoundException("checkup not found with this id");
        }
        List<Test> tests = testRepository.findByCheckUpsId(checkUpId);
        if(tests.size()>0)
            return tests;
        else throw new NotFoundException("not found or not added");
    }

    @PostMapping("/tests")
    private Test createTest(@RequestBody Test test){
        return testRepository.save(test);
    }

    @PutMapping("/tests/{testId}")
    private Test updateTest(@PathVariable Long id, @Valid @RequestBody Test newTest) throws NotFoundException {
        return testRepository.findById(id).map(test -> {
            test.setCategory(newTest.getCategory());
            test.setMax(newTest.getMax());
            test.setMin(newTest.getMin());
            test.setTestName(newTest.getTestName());
            return testRepository.save(test);
        }).orElseThrow(()-> new NotFoundException("test not found"));
    }

    @DeleteMapping("tests/{testId}")
    private String deleteTest(@PathVariable Long id){
        testRepository.deleteById(id);
        return "test deleted";
    }



}
