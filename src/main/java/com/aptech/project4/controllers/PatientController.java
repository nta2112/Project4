package com.aptech.project4.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.aptech.project4.models.Patient;
import com.aptech.project4.repositories.PatientRepository;

@Controller
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    // Danh sách
    @GetMapping("/patients")
    public String listPatients(Model model) {
        List<Patient> patients = patientRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("patients", patients);
        return "patients";
    }

    // Thêm
    @PostMapping("/patients/create")
    @ResponseBody
    public Patient addMedicine(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    // Xóa
    @DeleteMapping("/patient/delete/{id}")
    @ResponseBody
    public ResponseEntity<?> deletePatient(@PathVariable("id") Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            patientRepository.delete(patient.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("bệnh nhân không tồn tại!");
        }
    }

    // Edit
    @GetMapping("/patient/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy Bệnh nhân"));
        return ResponseEntity.ok(patient);
    }

    // Update
    @PutMapping("/patient/update/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patientDetails) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy Bệnh nhân"));

        // Cập nhật thông tin bệnh nhân
        patient.setFullName(patientDetails.getFullName());
        patient.setPhone(patientDetails.getPhone());
        patient.setEmail(patientDetails.getEmail());
        patient.setAddress(patientDetails.getAddress());
        patient.setDob(patientDetails.getDob());
        patient.setGender(patientDetails.getGender());

        return ResponseEntity.ok(patientRepository.save(patient));
    }

}
