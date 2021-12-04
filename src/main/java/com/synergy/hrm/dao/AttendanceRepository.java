package com.synergy.hrm.dao;

import java.util.List;

import com.synergy.hrm.model.Attendance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByEmployeeId(long employeeId);

    Attendance findByEmployeeIdAndId(long employeeId, long id);

}