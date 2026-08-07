package com.tcs.hospital.controller;

import com.tcs.hospital.model.Patient;
import com.tcs.hospital.repository.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    // GET http://localhost:8080/patients
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // POST http://localhost:8080/patients
    @PostMapping
    public Patient addPatient(@Valid @RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    // GET http://localhost:8080/patients/doctor/{doctorId}
    @GetMapping("/doctor/{doctorId}")
    public List<Patient> getPatientsByDoctor(@PathVariable Integer doctorId) {
        return patientRepository.findByDoctorId(doctorId);
    }

    // PUT http://localhost:8080/patients/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Integer id, @Valid @RequestBody Patient patientDetails) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + id));

        existingPatient.setName(patientDetails.getName());
        existingPatient.setAge(patientDetails.getAge());
        existingPatient.setDisease(patientDetails.getDisease());

        if (patientDetails.getDoctor() != null) {
            existingPatient.setDoctor(patientDetails.getDoctor());
        }

        Patient updatedPatient = patientRepository.save(existingPatient);
        return ResponseEntity.ok(updatedPatient);
    }

    // DELETE http://localhost:8080/patients/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Integer id) {
        if (!patientRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Patient with ID " + id + " does not exist!");
        }
        patientRepository.deleteById(id);
        return ResponseEntity.ok("Patient with ID " + id + " deleted successfully!");
    }
}