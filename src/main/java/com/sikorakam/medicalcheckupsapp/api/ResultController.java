package com.sikorakam.medicalcheckupsapp.api;

import com.sikorakam.medicalcheckupsapp.dao.CheckUpsRepo;
import com.sikorakam.medicalcheckupsapp.dao.ResultsRepository;
import com.sikorakam.medicalcheckupsapp.dao.TestRepository;
import com.sikorakam.medicalcheckupsapp.dao.entity.Result;
import com.sikorakam.medicalcheckupsapp.dao.entity.Test;
import com.sikorakam.medicalcheckupsapp.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ResultController {

    @Autowired
    private ResultsRepository resultsRepository;

    @Autowired
    private CheckUpsRepo checkUpsRepo;

    @Autowired
    private TestRepository testRepository;

    @GetMapping("/results")
    public List<Result> findAllResults(){
        return resultsRepository.findAll();
    }

    @GetMapping("/checkups/{checkupId}/results")
    public Result findResultByCheckUpId(@PathVariable (value = "checkupId") Long checkUpId) throws NotFoundException {
        if(!checkUpsRepo.existsById(checkUpId)){
            throw new NotFoundException("checkup not found with this id");
        }
        List<Result> results = resultsRepository.findByCheckUpId(checkUpId);
        if(results.size()>0)
            return results.get(0);
        else throw new NotFoundException("not found");
    }
    @PostMapping("/checkups/{checkupId}/results")
    public Result addResult(@PathVariable (value = "checkupId") Long checkupId, @Valid @RequestBody Result result) throws NotFoundException {
        return checkUpsRepo.findById(checkupId)
                .map(checkUp -> {
                    result.setCheckUp(checkUp);
                    return resultsRepository.save(result);
                }).orElseThrow(()->new NotFoundException("checkUp not found"));
    }

    @PostMapping("/checkups/{checkupId}/results/{testId}")
    public Result addResultWithTest(@PathVariable (value = "checkupId") Long checkupId,@PathVariable(value = "testId") Long testId, @Valid @RequestBody Result result) throws NotFoundException {
        Optional<Test> optionalTest = testRepository.findById(testId);
        return checkUpsRepo.findById(checkupId).map(checkUp -> {
            result.setTest(optionalTest.get());
            result.setCheckUp(checkUp);
            return resultsRepository.save(result);
        }).orElseThrow(()->new NotFoundException("checkup not found"));

    }


    @PutMapping("results/{resultId}")
    public Result updateResult(@PathVariable (value = "resultId") Long resultId, @Valid @RequestBody Result resultNew) throws NotFoundException {
        return resultsRepository.findById(resultId).map(result -> {
            result.setValue(resultNew.getValue());
            return resultsRepository.save(result);
        }).orElseThrow(()->new NotFoundException("Result not found"));
    }

    @PutMapping("results/{resultId}/{testId}")
    public Result setTestToResult(@PathVariable (value = "resultId") Long resultId, @PathVariable (value = "testId") Long testId) throws NotFoundException {

        Optional<Test> optionalTest = testRepository.findById(testId);
        Optional<Result> optionalResult = resultsRepository.findById(resultId);
        optionalResult.get().setTest(optionalTest.get());
        return resultsRepository.save(optionalResult.get());
    }

    @DeleteMapping("results/{resultId}")
    public String  deleteResult(@PathVariable Long resultId) throws NotFoundException {
        return resultsRepository.findById(resultId)
                .map(result -> {
                    resultsRepository.delete(result);
                    return "Result Deleted";
                }).orElseThrow(()->new NotFoundException("Result not found"));
    }

}
