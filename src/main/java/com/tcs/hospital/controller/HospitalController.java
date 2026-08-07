package com.tcs.hospital.controller;

import com.tcs.hospital.model.Hospital;
import com.tcs.hospital.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospitals")
@CrossOrigin(origins = "*")
public class HospitalController {

    @Autowired
    private HospitalRepository hospitalRepository;

    // GET http://localhost:8080/hospitals
    @GetMapping
    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    // POST http://localhost:8080/hospitals
    @PostMapping
    public Hospital createHospital(@RequestBody Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    // DELETE http://localhost:8080/hospitals/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHospital(@PathVariable Integer id) {
        if (!hospitalRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Hospital with ID " + id + " does not exist!");
        }
        hospitalRepository.deleteById(id);
        return ResponseEntity.ok("Hospital with ID " + id + " deleted successfully!");
    }
}