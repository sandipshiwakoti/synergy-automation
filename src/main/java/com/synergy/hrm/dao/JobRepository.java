package com.synergy.hrm.dao;

import java.util.Optional;

import com.synergy.hrm.model.Job;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

}
