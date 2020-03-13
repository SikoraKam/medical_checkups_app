package com.sikorakam.medicalcheckupsapp.dao;

import com.sikorakam.medicalcheckupsapp.dao.entity.CheckUp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CheckUpsRepo extends JpaRepository<CheckUp, Long> {
    Iterable<CheckUp> findByClientId(Long clientId);
    Optional<CheckUp> findByIdAndClientId(Long id, Long clientId);
}
