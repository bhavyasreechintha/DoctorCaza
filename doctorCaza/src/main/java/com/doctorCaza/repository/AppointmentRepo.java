package com.doctorCaza.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctorCaza.entity.Appointment;

public interface AppointmentRepo extends JpaRepository<Appointment, Long>{

	List<Appointment> findByDoctorId(Long doctorId);

}
