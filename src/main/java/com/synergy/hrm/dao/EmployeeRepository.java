package com.synergy.hrm.dao;

import com.synergy.hrm.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "SELECT id FROM EMPLOYEE WHERE EMAIL = ?1", nativeQuery = true)
    long findIdByEmail(String email);

    @Query(value = "SELECT * FROM EMPLOYEE WHERE EMAIL = ?1", nativeQuery = true)
    Employee findEmployeeByEmail(String email);

    @Query(value = "update employee set EMAIL = ?1 where id = ?2", nativeQuery = true)
    Boolean updateEmployeeEmail(String email, long id);
}
