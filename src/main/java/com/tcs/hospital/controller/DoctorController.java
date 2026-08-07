package com.tcs.hospital.controller;

import com.tcs.hospital.model.Doctor;
import com.tcs.hospital.repository.DoctorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@CrossOrigin(origins = "*")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    // GET http://localhost:8080/doctors
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // POST http://localhost:8080/doctors
    @PostMapping
    public Doctor addDoctor(@Valid @RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // GET http://localhost:8080/doctors/hospital/{hospitalId}
    @GetMapping("/hospital/{hospitalId}")
    public List<Doctor> getDoctorsByHospital(@PathVariable Integer hospitalId) {
        return doctorRepository.findByHospitalId(hospitalId);
    }

    // PUT http://localhost:8080/doctors/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Integer id, @Valid @RequestBody Doctor doctorDetails) {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with ID: " + id));

        existingDoctor.setName(doctorDetails.getName());
        existingDoctor.setSpecialization(doctorDetails.getSpecialization());

        if (doctorDetails.getHospital() != null) {
            existingDoctor.setHospital(doctorDetails.getHospital());
        }

        Doctor updatedDoctor = doctorRepository.save(existingDoctor);
        return ResponseEntity.ok(updatedDoctor);
    }

    // DELETE http://localhost:8080/doctors/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Integer id) {
        if (!doctorRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Doctor with ID " + id + " does not exist!");
        }
        doctorRepository.deleteById(id);
        return ResponseEntity.ok("Doctor with ID " + id + " deleted successfully!");
    }
}