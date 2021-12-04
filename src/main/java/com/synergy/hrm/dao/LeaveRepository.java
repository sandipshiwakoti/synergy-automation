package com.synergy.hrm.dao;

import com.synergy.hrm.model.Leave;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<Leave, Long> {

}
