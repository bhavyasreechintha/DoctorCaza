package com.doctorCaza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctorCaza.entity.Appointment;
import com.doctorCaza.service.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	AppointmentService appointmentService;

	@GetMapping("/doctor/{doctorId}")
	public List<Appointment> getAppointmentsByDoctorId(@PathVariable Long doctorId) {
		return appointmentService.getAppointmentsByDoctorId(doctorId);
	}

	




	@GetMapping("/{id}")
	public Appointment getAppointmentById(@PathVariable Long id) {
		return appointmentService.getAppointmentById(id);
	}

	@PostMapping("/saveAppointment")
	public Appointment createAppointment(@RequestBody Appointment appointment) {
		return appointmentService.createAppointment(appointment);
	}

	@PutMapping("/update/{id}")
	public Appointment updateAppointment(@PathVariable Long id, @RequestBody Appointment appointmentDetails) {
		return appointmentService.updateAppointment(id, appointmentDetails);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteAppointment(@PathVariable Long id) {
		appointmentService.deleteAppointment(id);
		return ResponseEntity.ok().build();
	}

	public AppointmentController(AppointmentService appointmentService) {
		super();
		this.appointmentService = appointmentService;
	}

}
