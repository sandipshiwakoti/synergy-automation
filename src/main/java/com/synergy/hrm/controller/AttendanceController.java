package com.synergy.hrm.controller;

import java.util.List;

import com.synergy.hrm.dao.AttendanceRepository;
import com.synergy.hrm.dao.EmployeeRepository;
import com.synergy.hrm.model.Attendance;
import com.synergy.hrm.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
// @CrossOrigin("http://localhost:3000")
@CrossOrigin(origins = "*")
// @CrossOrigin("https://synergy-hrm.herokuapp.com")
public class AttendanceController {
    @Autowired
    AttendanceRepository attendanceRepo;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/attendances")
    public List<Attendance> getAttendances() {
        return attendanceRepo.findAll();
    }

    @PostMapping("/attendances/{employeeId}")
    public Attendance getAttendances(@PathVariable("employeeId") long employeeId, @RequestBody Attendance attendance) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee != null) {
            attendance.setEmployee(employee);
            return attendanceRepo.save(attendance);
        }
        return null;
    }

    @GetMapping("/attendances/{id}")
    public Attendance getAttendanceById(@PathVariable("id") long id) {
        return attendanceRepo.findById(id).orElse(null);
    }

    @PutMapping(value = "/attendances/{id}", consumes = "application/json", produces = "application/json")
    public Attendance updateAttendanceById(@PathVariable long id, @RequestBody Attendance attendance) {
        Attendance result = attendanceRepo.findById(id).orElse(null);
        if (result != null) {
            result.setClockIn(attendance.getClockIn());
            result.setClockOut(attendance.getClockOut());
            return attendanceRepo.save(result);
        }
        return result;
    }

    @DeleteMapping("/attendances/{id}")
    public Attendance removeAttendanceById(@PathVariable("id") long id) {
        Attendance result = attendanceRepo.findById(id).orElse(null);
        if (result != null) {
            attendanceRepo.deleteById(id);
        }
        return result;
    }
}