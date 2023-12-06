package com.doctorCaza.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctorCaza.entity.Appointment;
import com.doctorCaza.repository.AppointmentRepo;
import com.doctorCaza.service.AppointmentService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	AppointmentRepo appointmentRepo;

	@Override
	public List<Appointment> getAppointmentsByDoctorId(Long doctorId) {

		return appointmentRepo.findByDoctorId(doctorId);
	}

	@Override
	public Appointment getAppointmentById(Long id) {
		return appointmentRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Appointment not found with id:" + id));
	}

	@Override
	public Appointment createAppointment(Appointment appointment) {
		return appointmentRepo.save(appointment);
	}

	@Override
	public Appointment updateAppointment(Long id, Appointment appointment) {
		Appointment appointmentToUpdate = getAppointmentById(id);

		if (appointmentToUpdate != null) {
			appointmentToUpdate.setDoctor(appointment.getDoctor());
			appointmentToUpdate.setPatient(appointment.getPatient());
			appointmentToUpdate.setDateTime(appointment.getDateTime());

			return appointmentRepo.save(appointmentToUpdate);
		} else {
			throw new EntityNotFoundException("Appointment not found with id: " + id + " for update");
		}
	}

	@Override
	public void deleteAppointment(Long id) {
		Appointment appointmentToDelete = getAppointmentById(id);

		if (appointmentToDelete != null) {
			appointmentRepo.delete(appointmentToDelete);
		} else {
			throw new EntityNotFoundException("Appointment not found with id: " + id + " for deletion");
		}
	}
}