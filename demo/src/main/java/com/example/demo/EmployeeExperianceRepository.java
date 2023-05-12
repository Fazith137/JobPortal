package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeExperianceRepository extends JpaRepository<EmployeeExperience, Long> {
    List<EmployeeExperience> findByEmployee(Employee employee);
}
