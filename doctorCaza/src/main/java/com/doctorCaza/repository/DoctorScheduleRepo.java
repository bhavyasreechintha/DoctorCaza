package com.doctorCaza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctorCaza.entity.DoctorSchedule;

public interface DoctorScheduleRepo extends JpaRepository<DoctorSchedule, Long> {

	List<DoctorSchedule> findByDoctorId(Long doctorId);

}
