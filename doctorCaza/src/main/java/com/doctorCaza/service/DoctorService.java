package com.doctorCaza.service;

import java.util.List;

import com.doctorCaza.entity.Doctor;
import com.doctorCaza.exception.ResourceNotFoundException;

public interface DoctorService {

	List<Doctor> getAllDoctors();

	Doctor getDoctorById(Long id);

	Doctor saveDoctor(Doctor doctor);

	Doctor updateDoctor(Long id, Doctor doctor) throws ResourceNotFoundException;

	void deleteDoctor(Long id);

}
