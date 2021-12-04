package com.synergy.hrm.controller;

import com.synergy.hrm.dao.EmployeeRepository;
import com.synergy.hrm.dao.JobRepository;
import com.synergy.hrm.dao.UserRepository;
import com.synergy.hrm.model.Employee;
import com.synergy.hrm.model.Job;
import com.synergy.hrm.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    UserRepository userRepo;

    @Autowired
    EmployeeRepository employeeRepo;

    @Autowired
    JobRepository jobRepo;

    @PostMapping(value = "/auth", consumes = "application/json", produces = "application/json")
    public Boolean checkAuth(@RequestBody User user) {
        User u = userRepo.findByEmailAndPasswordAndRole(user.getEmail(), user.getPassword(), user.getRole());
        if (u != null) {
            return true;
        }
        return false;
    }

    @PostMapping(value = "/auth/user", consumes = "application/json", produces = "application/json")
    public Boolean createUser(@RequestBody User user) {
        Job job = new Job();
        Employee employee = new Employee();
        employee.setJob(jobRepo.save(job));
        employeeRepo.updateEmployeeEmail(user.getEmail(), employee.getId());
        userRepo.save(user);
        return true;
    }
}
