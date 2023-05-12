package com.example.demo;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeService;

    @Autowired
    private EmployeeExperianceRepository employeeExperienceService;

    @Autowired
    private EmployeeSkillsRepository employeeSkillsService;



    public EmployeeDTO getEmployeeDetailsById(Long id) {
        Optional<Employee> employee = employeeService.findById(id);
        if (employee.isPresent()) {
            List<EmployeeExperience> experience = employeeExperienceService.findByEmployee(employee.get());
            List<EmployeeSkills> skills = employeeSkillsService.findByEmployee(employee.get());
            return new EmployeeDTO(employee.get(), experience, skills);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id " + id);
        }
    }

    @Transactional
    public Employee addEmployee(EmployeeRequest employeeRequest) {
        // create and save employee entity
        Employee employee = new Employee();
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setAccountType(employeeRequest.getAccountType());
        employee.setEducation(employeeRequest.getEducation());
        employee.setPassword(employeeRequest.getPassword());
        employee = employeeService.save(employee);

        // create and save employee experience entities
        for (ExperienceRequest experienceRequest : employeeRequest.getExperiences()) {
            EmployeeExperience experience = new EmployeeExperience();
            experience.setEmployee(employee);
            experience.setCompanyName(experienceRequest.getCompanyName());
            experience.setStartDate(experienceRequest.getStartDate());
            experience.setEndDate(experienceRequest.getEndDate());
            experience.setJobTitle(experienceRequest.getJobTitle());
            experience.setSector(experienceRequest.getSector());
            experience.setEmployeeType(experienceRequest.getEmployeeType());
            employeeExperienceService.save(experience);
        }

        // create and save employee skill entities
        for (String skillName : employeeRequest.getSkills()) {
            EmployeeSkills skill = new EmployeeSkills();
            skill.setEmployee(employee);
            skill.setSkillName(skillName);
            employeeSkillsService.save(skill);
        }

        return employee;
    }

    public void deleteEmployeeById(Long id) throws Exception {
        // Check if employee exists
        Optional<Employee> optionalEmployee = employeeService.findById(id);
        if (!optionalEmployee.isPresent()) {
            throw new Exception("Employee not found with ID " + id);
        }

        // Delete employee's work experiences
        List<EmployeeExperience> experiences = employeeExperienceService.findByEmployee(optionalEmployee.get());
        employeeExperienceService.deleteAll(experiences);

        // Delete employee's skills
        List<EmployeeSkills> skills = employeeSkillsService.findByEmployee(optionalEmployee.get());
        employeeSkillsService.deleteAll(skills);

        // Delete employee
        employeeService.deleteById(id);
    }

    public void updateEmployeeById(Long id, EmployeeRequest employeeDto) throws Exception {
        // Check if employee exists
        Optional<Employee> optionalEmployee = employeeService.findById(id);
        if (!optionalEmployee.isPresent()) {
            throw new Exception("Employee not found with ID " + id);
        }

        // Update employee
        Employee employee = optionalEmployee.get();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setAccountType(employeeDto.getAccountType());
        employee.setPassword(employeeDto.getPassword());
        employeeService.save(employee);

        // Update employee's work experiences
        List<ExperienceRequest> experiences = employeeDto.getExperiences();
        for (ExperienceRequest experience : experiences) {
            EmployeeExperience employeeExperience=new EmployeeExperience();
            employeeExperience.setEmployeeType(experience.getEmployeeType());
            employeeExperience.setSector(experience.getSector());
            employeeExperience.setStartDate(experience.getStartDate());
            employeeExperience.setEndDate(experience.getEndDate());
            employeeExperience.setJobTitle(experience.getJobTitle());
            employeeExperience.setCompanyName(experience.getCompanyName());
            employeeExperience.setEmployee(employee);
            employeeExperienceService.save(employeeExperience);
        }

        // Update employee's skills
        List<String> skills = employeeDto.getSkills();
        for (String skill : skills) {
            EmployeeSkills employeeSkills=new EmployeeSkills();
            employeeSkills.setSkillName(skill);
            employeeSkills.setEmployee(employee);
            employeeSkillsService.save(employeeSkills);
        }
    }

    public boolean login(String email, String password){

        Employee employee = employeeService.findByEmailAndPassword(email, password);
        if (employee != null) {
            return true;
        } else {
            return false;
        }
    }
}
