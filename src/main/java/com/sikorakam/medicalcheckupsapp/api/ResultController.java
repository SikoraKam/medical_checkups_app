package com.sikorakam.medicalcheckupsapp.api;

import com.sikorakam.medicalcheckupsapp.dao.CheckUpsRepo;
import com.sikorakam.medicalcheckupsapp.dao.ResultsRepository;
import com.sikorakam.medicalcheckupsapp.dao.entity.Result;
import com.sikorakam.medicalcheckupsapp.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.jvm.hotspot.debugger.Page;

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

    @GetMapping("checkups/{checkupId}/results")
    public Page<Result> findAllResultsByCheckUpId(@PathVariable (value = "checkupId") Long checkUpId, Pageable pageable){
        return resultsRepository.findAll();
    }

    @GetMapping("/checkups/{checkupId}/results")
    public Result findResultByCheckUpId(@PathVariable (value = "checkupId") Long checkUpId) throws NotFoundException {
        if(!checkUpsRepo.existsById(checkUpId)){
            throw new NotFoundException("chechup not found with this id");
        }
        List<Result> results = resultsRepository.findByCheckUpId(checkUpId);
        if(results.size()>0)
            return results.get(0);
        else throw new NotFoundException("not found");
    }
    @PostMapping("/checkups/{checkupId}/results")
    public Result addResult(@PathVariable (value = "checkupId") Long checkUpId,@Valid @RequestBody Result result) throws NotFoundException {
        return checkUpsRepo.findById(checkUpId)
                .map(checkUp -> {
                    result.setCheckUp(checkUp);
                    return resultsRepository.save(result);
                }).orElseThrow(()->new NotFoundException("checkUp not found"));
    }
    @PutMapping("results/{resultId}")
    public Result updateResult(@PathVariable Result result){
        return resultsRepository.save(result);
    }

    @DeleteMapping("results/{resultId}")
    public String  deleteResult(@RequestParam @PathVariable Long resultId) throws NotFoundException {
        return resultsRepository.findById(resultId)
                .map(result -> {
                    resultsRepository.delete(result);
                    return "Deleted";
                }).orElseThrow(()->new NotFoundException("Result not found"));
    }

}
