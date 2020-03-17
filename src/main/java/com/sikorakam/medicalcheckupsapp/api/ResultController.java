package com.sikorakam.medicalcheckupsapp.api;

import com.sikorakam.medicalcheckupsapp.dao.CheckUpsRepo;
import com.sikorakam.medicalcheckupsapp.dao.ResultsRepository;
import com.sikorakam.medicalcheckupsapp.dao.entity.Result;
import com.sikorakam.medicalcheckupsapp.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ResultController {

    @Autowired
    private ResultsRepository resultsRepository;

    @Autowired
    private CheckUpsRepo checkUpsRepo;

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
    @PutMapping("results/{resultId}")
    public Result updateResult(@PathVariable (value = "resultId") Long resultId, @Valid @RequestBody Result resultNew) throws NotFoundException {
        return resultsRepository.findById(resultId).map(result -> {
            result.setValue(resultNew.getValue());
            return resultsRepository.save(result);
        }).orElseThrow(()->new NotFoundException("Result not found"));
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
