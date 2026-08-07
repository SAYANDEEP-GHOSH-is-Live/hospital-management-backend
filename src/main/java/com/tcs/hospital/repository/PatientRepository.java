package com.tcs.hospital.repository;

import com.tcs.hospital.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    // Custom query to fetch patients assigned to a specific Doctor ID
    List<Patient> findByDoctorId(Integer doctorId);
}