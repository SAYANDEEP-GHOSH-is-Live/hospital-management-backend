package com.tcs.hospital.repository;

import com.tcs.hospital.model.Doctor;
import com.tcs.hospital.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
}