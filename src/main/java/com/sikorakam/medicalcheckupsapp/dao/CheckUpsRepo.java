package com.sikorakam.medicalcheckupsapp.dao;

import com.sikorakam.medicalcheckupsapp.dao.entity.CheckUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckUpsRepo extends JpaRepository<CheckUp, Long> {
}
