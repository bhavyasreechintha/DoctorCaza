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

import com.doctorCaza.entity.Doctor;
import com.doctorCaza.exception.DoctorNotFoundException;
import com.doctorCaza.exception.ResourceNotFoundException;
import com.doctorCaza.service.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	DoctorService docService;

	@GetMapping("/getDoctors")
	 public ResponseEntity<?> getAllDoctors() {
        try {
            List<Doctor> doctors = docService.getAllDoctors();
            return ResponseEntity.ok().body(doctors);
        } catch (DoctorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
	}
	
	@GetMapping("/getDoctors/{id}")
	public Doctor getDoctorById(@PathVariable Long id) {
		return docService.getDoctorById(id);

	}

	@PostMapping("/saveDoctor")
	public String saveDoctor(@RequestBody Doctor doctor) {
		docService.saveDoctor(doctor);
		return "data saved successfully";

	}

	@PutMapping("/updateDoctor/{id}")
	public Doctor updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) throws ResourceNotFoundException {
		return docService.updateDoctor(id, doctor);

	}

	@DeleteMapping("deleteDoctor/{id}")
	public ResponseEntity<?> deleteDoctor(@PathVariable Long id) {
		docService.deleteDoctor(id);
		return ResponseEntity.ok().build();
	}

	public DoctorController(DoctorService docService) {
		super();
		this.docService = docService;
	}}