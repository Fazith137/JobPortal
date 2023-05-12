package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceRequest {

    private String companyName;

    private Date startDate;

    private Date endDate;

    private String jobTitle;

    private String sector;

    private String employeeType;


}
