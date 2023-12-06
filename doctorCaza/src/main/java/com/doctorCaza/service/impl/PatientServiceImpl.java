package com.doctorCaza.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctorCaza.entity.Patient;
import com.doctorCaza.exception.ResourceNotFoundException;
import com.doctorCaza.repository.PatientRepo;
import com.doctorCaza.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {
	@Autowired
	PatientRepo patientRepo;

	@Override
	public List<Patient> getAllPatients() {
		List<Patient> patients = patientRepo.findAll();
		if (patients.isEmpty()) {
			throw new ResourceNotFoundException("No patients found");
		}
		return patients;
	}

	@Override
	public Patient getPatientById(Long id) {
		return patientRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
	}

	@Override
	public Patient createPatient(Patient patient) {
		return patientRepo.save(patient);
	}

	@Override
	public Patient updatePatient(Long id, Patient updatedPatient) {
		Patient patient = getPatientById(id);

		if (patient != null) {
			patient.setName(updatedPatient.getName());
			patient.setAge(updatedPatient.getAge());

			return patientRepo.save(patient);
		} else {
			throw new ResourceNotFoundException("Patient not found with id: " + id + " for update");
		}
	}

	@Override
	public void deletePatient(Long id) {
		Optional<Patient> optionalPatient = patientRepo.findById(id);

		if (optionalPatient.isPresent()) {
			patientRepo.delete(optionalPatient.get());
		} else {
			throw new ResourceNotFoundException("Patient not found with id: " + id + " for deletion");
		}
	}
}