package com.doctorCaza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctorCaza.entity.Doctor;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {

}
