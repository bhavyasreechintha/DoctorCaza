package com.doctorCaza.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doctorCaza.entity.DoctorSchedule;
import com.doctorCaza.service.DoctorScheduleService;

@RestController
@RequestMapping("/doctor-schedule")
public class DoctorScheduleController {

	
	
	@Autowired
    private DoctorScheduleService doctorScheduleService;

    @PostMapping("/create-slots/{doctorId}")
    public ResponseEntity<List<DoctorSchedule>> createAvailableSlots(
            @PathVariable Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<DoctorSchedule> availableSlots = doctorScheduleService.saveAvailableSlotsForDoctor(doctorId, date);
        return ResponseEntity.ok(availableSlots);
    }

    @GetMapping("/available-slots/{doctorId}")
    public ResponseEntity<List<DoctorSchedule>> getAvailableSlots(@PathVariable Long doctorId) {
        List<DoctorSchedule> availableSlots = doctorScheduleService.getAvailableSlotsForDoctor(doctorId);
        return ResponseEntity.ok(availableSlots);
    }
    
    
    
    
    @GetMapping("/all")
    public ResponseEntity<List<DoctorSchedule>> getAllDoctorSchedules() {
        List<DoctorSchedule> allSchedules = doctorScheduleService.getAllDoctorSchedules();
        return ResponseEntity.ok(allSchedules);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorSchedule> getDoctorScheduleById(@PathVariable Long id) {
        DoctorSchedule schedule = doctorScheduleService.getDoctorScheduleById(id);
        return ResponseEntity.ok(schedule);
    }

	
    @PutMapping("/update/{id}")
    public ResponseEntity<DoctorSchedule> updateDoctorSchedule(
            @PathVariable Long id,
            @RequestBody DoctorSchedule schedule) {
        DoctorSchedule updatedSchedule = doctorScheduleService.updateDoctorSchedule(id, schedule);
        return ResponseEntity.ok(updatedSchedule);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDoctorSchedule(@PathVariable Long id) {
        doctorScheduleService.deleteDoctorSchedule(id);
        return ResponseEntity.ok("Doctor Schedule with ID: " + id + " deleted successfully");
    }}
