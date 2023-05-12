package com.example.demo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String accountType;

    private String education;

    private String password;

    private List<ExperienceRequest> experiences;

    private List<String> skills;

    // constructor, getters and setters
}
