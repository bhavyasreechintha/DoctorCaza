package com.doctorCaza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctorCaza.entity.Patient;

public interface PatientRepo extends JpaRepository<Patient, Long> {

}
