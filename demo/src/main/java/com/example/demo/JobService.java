package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    @Autowired
    private JobRepo jobRepo;

    public List<JobEntity> getAllEmployees() {
        return jobRepo.findAll();
    }

    public Optional<JobEntity> getEmployeeById(Long id) {
        return jobRepo.findById(id);
    }

    public JobEntity addEmployee(JobEntity employee) {
        return jobRepo.save(employee);
    }

    public void deleteEmployeeById(Long id) {
        jobRepo.deleteById(id);
    }

    public JobEntity updateEmployee(JobEntity jobEntity) {
        return jobRepo.save(jobEntity);
    }

    public JobEntity findByUsername(String username) {
        return jobRepo.findByEmail(username);
    }

    public boolean authenticate(String username, String password) {
        JobEntity user = findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
}
