package com.synergy.hrm.controller;

import java.util.List;

import com.synergy.hrm.dao.EmployeeRepository;
import com.synergy.hrm.dao.LeaveRepository;
import com.synergy.hrm.model.Employee;
import com.synergy.hrm.model.Leave;

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
public class LeaveController {
    @Autowired
    LeaveRepository leaveRepo;

    @Autowired
    EmployeeRepository employeeRepo;

    @GetMapping("/leaves")
    public List<Leave> getLeaves() {
        return leaveRepo.findAll();
    }

    @GetMapping("/leaves/{id}")
    public Leave getLeave(@PathVariable("id") long id) {
        return leaveRepo.findById(id).orElse(null);
    }

    @PutMapping(value = "/leaves/{id}", consumes = "application/json", produces = "application/json")
    public Leave updateLeave(@PathVariable long id, @RequestBody Leave leave) {
        Leave result = leaveRepo.findById(id).orElse(null);
        if (result != null) {
            result.setAbsenceFrom(leave.getAbsenceFrom());
            result.setDays(leave.getDays());
            result.setReasons(leave.getReasons());
            result.setType(leave.getType());
            result.setStatus(leave.getStatus());
            return leaveRepo.save(result);
        }
        return result;
    }

    @DeleteMapping("/leaves/{id}")
    public Leave removeLeave(@PathVariable("id") long id) {
        Leave result = leaveRepo.findById(id).orElse(null);
        if (result != null) {
            leaveRepo.deleteById(id);
        }
        return result;
    }

    @PostMapping(value = "/leaves/employees/{employeeId}", consumes = "application/json", produces = "application/json")
    public Leave addLeave(@PathVariable("employeeId") long employeeId, @RequestBody Leave leave) {
        Employee employee = employeeRepo.findById(employeeId).orElse(null);
        if (employee != null) {
            leave.setEmployee(employee);
            return leaveRepo.save(leave);
        }
        return null;
    }
}