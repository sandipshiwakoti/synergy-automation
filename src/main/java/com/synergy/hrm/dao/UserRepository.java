package com.synergy.hrm.dao;

import com.synergy.hrm.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);

    User findByEmailAndPasswordAndRole(String email, String password, String role);

}
