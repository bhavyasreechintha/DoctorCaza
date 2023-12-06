package com.doctorCaza.service;

import java.util.List;

import com.doctorCaza.entity.Appointment;

public interface AppointmentService {

	List<Appointment> getAppointmentsByDoctorId(Long doctorId);

	Appointment getAppointmentById(Long id);

	Appointment createAppointment(Appointment appointment);

	Appointment updateAppointment(Long id, Appointment appointment);

	void deleteAppointment(Long id);

}
