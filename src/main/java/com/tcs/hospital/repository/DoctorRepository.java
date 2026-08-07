package com.tcs.hospital.repository;

import com.tcs.hospital.model.Doctor;
import com.tcs.hospital.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    // Spring turns this method name into:
    // SELECT * FROM doctor WHERE hospital_id = ?
    List findByHospitalId(Integer hospitalId);
}