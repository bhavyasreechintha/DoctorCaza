package com.doctorCaza.service;

import java.time.LocalDate;
import java.util.List;

import com.doctorCaza.entity.DoctorSchedule;

public interface DoctorScheduleService {
	
	public List<DoctorSchedule> getAvailableSlotsForDoctor(Long doctorId);
	
	public List<DoctorSchedule> saveAvailableSlotsForDoctor(Long doctorId, LocalDate date) ;
	public List<DoctorSchedule> getAllDoctorSchedules() ;
	
	
	public DoctorSchedule getDoctorScheduleById(Long id);
	public DoctorSchedule updateDoctorSchedule(Long id, DoctorSchedule schedule);
	
	public void deleteDoctorSchedule(Long id);
	
}
