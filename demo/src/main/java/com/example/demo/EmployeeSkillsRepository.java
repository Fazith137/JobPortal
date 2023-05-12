package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeSkillsRepository extends JpaRepository<EmployeeSkills, Long> {
    List<EmployeeSkills> findByEmployee(Employee employee);
}