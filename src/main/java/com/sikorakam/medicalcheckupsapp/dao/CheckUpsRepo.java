package com.sikorakam.medicalcheckupsapp.dao;

import com.sikorakam.medicalcheckupsapp.dao.entity.CheckUp;
import org.springframework.data.repository.CrudRepository;

public interface CheckUpsRepo extends CrudRepository<CheckUp, Long> {
}
