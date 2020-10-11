package com.sikorakam.medicalcheckupsapp.dao;

import com.sikorakam.medicalcheckupsapp.dao.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository  extends JpaRepository<Client,Long> {
       Client findByName (String name);

       Boolean existsByEmail(String email);
}
