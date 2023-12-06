package com.doctorCaza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctorCaza.entity.Patient;
import com.doctorCaza.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	PatientService patientService;

	@GetMapping("/all")
	public List<Patient> getAllPatients() {
		return patientService.getAllPatients();
	}

	@GetMapping("/all/{id}")
	public Patient getPatientById(@PathVariable Long id) {
		return patientService.getPatientById(id);
	}

	  @PostMapping("/save")
	    public ResponseEntity<?> createPatient(@RequestBody Patient patient) {
	        try {
	            Patient createdPatient = patientService.createPatient(patient);
	            return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating patient");
	        }}

	@PutMapping("/update/{id}")
	public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patients) {
		return patientService.updatePatient(id, patients);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletePatient(@PathVariable Long id) {
		patientService.deletePatient(id);
		return ResponseEntity.ok().build();
	}

	public PatientController(PatientService patientService) {
		super();
		this.patientService = patientService;
	}
}