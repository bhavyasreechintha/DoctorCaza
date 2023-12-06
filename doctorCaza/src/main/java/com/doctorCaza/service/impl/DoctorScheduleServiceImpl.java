package com.doctorCaza.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctorCaza.entity.Doctor;
import com.doctorCaza.entity.DoctorSchedule;
import com.doctorCaza.repository.DoctorRepo;
import com.doctorCaza.repository.DoctorScheduleRepo;
import com.doctorCaza.service.DoctorScheduleService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DoctorScheduleServiceImpl implements DoctorScheduleService {

	@Autowired
	private DoctorScheduleRepo doctorScheduleRepository;

	@Autowired
	private DoctorRepo doctorRepository;

	private static final Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);

	public List<DoctorSchedule> saveAvailableSlotsForDoctor(Long doctorId, LocalDate date) {
		Doctor doctor = doctorRepository.findById(doctorId)

				.orElseThrow(() -> new EntityNotFoundException("Doctor not found with ID: " + doctorId));
		logger.info("doctor not found");
		List<DoctorSchedule> availableSlots = new ArrayList<>();
		LocalDateTime startDateTime = date.atTime(9, 0);
		LocalDateTime endDateTime = date.atTime(17, 0);

		while (startDateTime.plusMinutes(30).isBefore(endDateTime)) {
			DoctorSchedule schedule = new DoctorSchedule();
			schedule.setDoctor(doctor);
			schedule.setStartTime(startDateTime);
			schedule.setEndTime(startDateTime.plusMinutes(30));
			doctorScheduleRepository.save(schedule);

			availableSlots.add(schedule);
			startDateTime = startDateTime.plusMinutes(30);
		}

		return availableSlots;
	}

	public List<DoctorSchedule> getAvailableSlotsForDoctor(Long doctorId) {
		return doctorScheduleRepository.findByDoctorId(doctorId);
	}

	public List<DoctorSchedule> getAllDoctorSchedules() {
		return doctorScheduleRepository.findAll();
	}

	public DoctorSchedule getDoctorScheduleById(Long id) {
		return doctorScheduleRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Doctor Schedule not found with ID: " + id));
	}

	public DoctorSchedule updateDoctorSchedule(Long id, DoctorSchedule schedule) {
		if (doctorScheduleRepository.existsById(id)) {
			schedule.setId(id);
			return doctorScheduleRepository.save(schedule);
		} else {
			throw new EntityNotFoundException("Doctor Schedule not found with ID: " + id);
		}
	}

	public void deleteDoctorSchedule(Long id) {
		doctorScheduleRepository.deleteById(id);
	}
}