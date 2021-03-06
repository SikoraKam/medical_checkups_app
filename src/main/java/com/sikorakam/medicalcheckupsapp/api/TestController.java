package com.sikorakam.medicalcheckupsapp.api;

import com.sikorakam.medicalcheckupsapp.dao.ResultsRepository;
import com.sikorakam.medicalcheckupsapp.dao.TestRepository;
import com.sikorakam.medicalcheckupsapp.dao.entity.Test;
import com.sikorakam.medicalcheckupsapp.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private ResultsRepository resultsRepository;

    @GetMapping("/tests")
    private List<Test> findAllTests(){
        return testRepository.findAll();
    }

    @GetMapping("/tests/{id}")
    private Optional<Test> findTestById(@PathVariable (name = "id") Long testId){
        return testRepository.findById(testId);
    }

    @GetMapping("/results/{resultId}/tests")
    private List<Test> findAllTestsByResultId(@PathVariable (name = "resultId") Long resultId) throws NotFoundException {
        if(!resultsRepository.existsById(resultId)){
            throw new NotFoundException("checkup not found with this id");
        }
        List<Test> tests = testRepository.findByResultsId(resultId);
        if(tests.size()>0)
            return tests;
        else throw new NotFoundException("not found or not added");
    }

    @PostMapping("/tests")
    private Test createTest(@RequestBody Test test){
        return testRepository.save(test);
    }

    @PutMapping("/tests/{testId}")
    private Test updateTest(@PathVariable (value = "testId")Long id, @Valid @RequestBody Test newTest) throws NotFoundException {
        return testRepository.findById(id).map(test -> {
            test.setCategory(newTest.getCategory());
            test.setMax(newTest.getMax());
            test.setMin(newTest.getMin());
            test.setTestName(newTest.getTestName());
            return testRepository.save(test);
        }).orElseThrow(()-> new NotFoundException("test not found"));
    }

    @DeleteMapping("tests/{testId}")
    private String deleteTest(@PathVariable (value = "testId") Long id){
        testRepository.deleteById(id);
        return "test deleted";
    }



}
