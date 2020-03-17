package com.sikorakam.medicalcheckupsapp.dao;

import com.sikorakam.medicalcheckupsapp.dao.entity.Result;
import com.sikorakam.medicalcheckupsapp.dao.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test,Long> {
    List<Test> findByCheckUpsId(Long checkUpId);
}
