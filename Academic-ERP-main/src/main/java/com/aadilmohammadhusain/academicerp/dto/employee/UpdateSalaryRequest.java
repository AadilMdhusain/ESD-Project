package com.aadilmohammadhusain.academicerp.dto.employee;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateSalaryRequest(
        @JsonProperty("employeeId") Long employeeId,
        @JsonProperty("newSalary") Double newSalary
) { }
