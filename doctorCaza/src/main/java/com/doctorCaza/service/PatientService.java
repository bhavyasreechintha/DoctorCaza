package com.doctorCaza.service;

import java.util.List;

import com.doctorCaza.entity.Patient;

public interface PatientService {

	List<Patient> getAllPatients();

	Patient getPatientById(Long id);

	Patient createPatient(Patient patient);

	Patient updatePatient(Long id, Patient patients);

	void deletePatient(Long id);

}
