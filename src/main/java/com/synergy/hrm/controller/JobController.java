package com.synergy.hrm.controller;

import java.util.List;

import com.synergy.hrm.dao.JobRepository;
import com.synergy.hrm.model.Job;

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

public class JobController {
    @Autowired
    JobRepository jobRepo;

    @GetMapping("/jobs")
    public List<Job> getJobs() {
        return jobRepo.findAll();
    }

    @GetMapping("/jobs/{id}")
    public Job getJob(@PathVariable("id") long id) {
        return jobRepo.findById(id).orElse(null);
    }

    @PutMapping(value = "/jobs/{id}", consumes = "application/json", produces = "application/json")
    public Job updateJob(@PathVariable long id, @RequestBody Job job) {
        Job result = jobRepo.findById(id).orElse(null);
        if (result != null) {
            result.setOfficeName(job.getOfficeName());
            result.setLevel(job.getLevel());
            result.setPosition(job.getPosition());
            result.setBranch(job.getBranch());
            result.setDepartment(job.getDepartment());
            result.setJoinedDate(job.getJoinedDate());
            result.setTeamNames(job.getTeamNames());
            result.setTransferHistoryDetails(job.getTransferHistoryDetails());
            return jobRepo.save(result);
        }
        return result;
    }

    @DeleteMapping("/jobs/{id}")
    public Job removeJob(@PathVariable("id") long id) {
        Job result = jobRepo.findById(id).orElse(null);
        if (result != null) {
            jobRepo.deleteById(id);
        }
        return result;
    }

    @PostMapping(value = "/jobs", consumes = "application/json", produces = "application/json")
    public Job addJobs(@RequestBody Job job) {
        return jobRepo.save(job);
    }
}