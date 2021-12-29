package com.synergy.hrm.controller;

import java.util.List;

import com.synergy.hrm.dao.EmployeeRepository;
import com.synergy.hrm.dao.JobRepository;
import com.synergy.hrm.model.Employee;
import com.synergy.hrm.model.Job;
import com.synergy.hrm.model.User;

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
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepo;
    @Autowired
    JobRepository jobRepo;

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeRepo.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getSingleEmployee(@PathVariable("id") long id) {
        return employeeRepo.findById(id).orElse(null);
    }

    @PostMapping(value = "/employees", consumes = "application/json", produces = "application/json")
    public Employee addEmployee(@RequestBody Employee employee) {
        Job job = new Job();
        employee.setJob(jobRepo.save(job));
        return employeeRepo.save(employee);
    }

    @DeleteMapping("/employees/{id}")
    public Employee removeEmployee(@PathVariable("id") long id) {
        Employee emp = employeeRepo.findById(id).orElse(null);
        if (emp != null) {
            employeeRepo.deleteById(id);
        }
        return emp;
    }

    @PutMapping(value = "/employees/{id}")
    public Employee updateEmployee(@PathVariable("id") long id, @RequestBody Employee updatedEmp) {
        Employee emp = employeeRepo.findById(id).orElse(null);
        Job job = emp.getJob();
        if (job != null) {
            updatedEmp.setJob(job);
            employeeRepo.save(updatedEmp);
            return updatedEmp;
        }
        return null;
    }

    @GetMapping("employees/{employeeId}/job")
    public Job getJobById(@PathVariable("employeeId") long employeeId) {
        Employee emp = employeeRepo.findById(employeeId).orElse(null);
        return emp.getJob();
    }

    // @PostMapping(value = "/employees/email/id", consumes = "application/json")
    // public long getIdByEmail(@RequestBody String email) {
    // return employeeRepo.findIdByEmail(email);
    // }

    @PostMapping(value = "/email/employee", consumes = "application/json")
    public Employee getEmployeeByEmail(@RequestBody User user) {
        System.out.println("hello" + user);
        return employeeRepo.findEmployeeByEmail(user.getEmail());
    }
}