package com.aadilmohammadhusain.academicerp.dto.employee;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record EmployeeSalaryRequest(
        @JsonProperty("employeeIds") List<Long> employeeIds  // List of employee IDs
) {
}
