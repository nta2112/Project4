package com.aptech.project4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aptech.project4.models.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
