package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/test2")
    public ResponseEntity<List<JobEntity>> getAllEmployees() {
        List<JobEntity> employees = jobService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("getemployee/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employee = employeeService.getEmployeeDetailsById(id);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }

    @PostMapping("/addemployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeService.addEmployee(employeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @DeleteMapping("deleteemployee/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id) {
        try {
            employeeService.deleteEmployeeById(id);
            return ResponseEntity.ok().body("Employee with ID " + id + " deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting employee with ID " + id);
        }
    }

    @PutMapping("/updateemployee/{id}")
    public ResponseEntity<String> updateEmployeeById(@PathVariable Long id, @RequestBody EmployeeRequest employeeDto) {
        try {
            employeeService.updateEmployeeById(id, employeeDto);
            return ResponseEntity.ok().body("Employee with ID " + id + " updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating employee with ID " + id);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestHeader("email") String email,@RequestHeader("password") String password) {

        if (employeeService.login(email,password)) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }


    @PostMapping("/test")
    public ResponseEntity<JobEntity> addEmployee(@RequestBody JobEntity employee) {
        JobEntity newEmployee = jobService.addEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }



    @PutMapping
    public ResponseEntity<JobEntity> updateEmployee(@RequestBody JobEntity employee) {
        JobEntity updatedEmployee = jobService.updateEmployee(employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }
}
