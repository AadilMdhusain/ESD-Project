package com.aadilmohammadhusain.academicerp.dto.employee;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EmployeeAccountRequest(
        @JsonProperty("employeeId")
        Long employeeId,

        @JsonProperty("initialBalance")
        Double initialBalance
) {
}
