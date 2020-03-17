package com.sikorakam.medicalcheckupsapp.dao;

import com.sikorakam.medicalcheckupsapp.dao.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultsRepository extends JpaRepository<Result,Long> {
    List<Result> findByCheckUpId(Long checkUpId);
}
