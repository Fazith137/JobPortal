package com.example.demo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployeeDTO {
    private Employee employee;
    private List<EmployeeExperience> experience;
    private List<EmployeeSkills> skills;

    public EmployeeDTO(Employee employee, List<EmployeeExperience> experience, List<EmployeeSkills> skills) {
        this.employee = employee;
        this.experience = experience;
        this.skills = skills;
    }

    // getters and setters
}

