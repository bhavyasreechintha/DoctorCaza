package com.doctorCaza.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctorCaza.entity.Doctor;
import com.doctorCaza.exception.DoctorNotFoundException;
import com.doctorCaza.exception.ResourceNotFoundException;
import com.doctorCaza.repository.DoctorRepo;
import com.doctorCaza.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {
	@Autowired
	DoctorRepo doctorRepo;

	@Override
	public List<Doctor> getAllDoctors() {
	    List<Doctor> doctors = doctorRepo.findAll();
	    if (doctors.isEmpty()) {
	        throw new DoctorNotFoundException("No doctors found");
	    }
	    return doctors;
	}
	@Override
	public Doctor getDoctorById(Long id) {

		return doctorRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
	}

	@Override
	public Doctor saveDoctor(Doctor doctor) {

		return doctorRepo.save(doctor);
	}

	@Override
	public Doctor updateDoctor(Long id, Doctor doctor) throws ResourceNotFoundException {

		Optional<Doctor> doc = doctorRepo.findById(doctor.getId());

		if (doc.isPresent()) {
			doctor.setId(id);
			return doctorRepo.save(doctor);
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + doctor.getId());
		}
	}

	@Override
	public void deleteDoctor(Long id) {
		doctorRepo.deleteById(id);

	}}