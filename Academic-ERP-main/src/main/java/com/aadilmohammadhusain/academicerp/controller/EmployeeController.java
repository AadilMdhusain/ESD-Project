package com.aadilmohammadhusain.academicerp.controller;

import com.aadilmohammadhusain.academicerp.dto.employee.EmployeeRequest;
import com.aadilmohammadhusain.academicerp.dto.employee.EmployeeResponse;
import com.aadilmohammadhusain.academicerp.dto.employee.EmployeeSalaryRequest;
import com.aadilmohammadhusain.academicerp.dto.employee.UpdateSalaryRequest;
import com.aadilmohammadhusain.academicerp.dto.employee.*;
import com.aadilmohammadhusain.academicerp.exception.ResourceNotFoundException;
import com.aadilmohammadhusain.academicerp.exception.BadRequestException;
import com.aadilmohammadhusain.academicerp.helper.JWTHelper;
import com.aadilmohammadhusain.academicerp.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final JWTHelper jwtHelper;

    @PatchMapping()
    public ResponseEntity<String> updatePassword(@RequestBody @Valid EmployeeRequest employeeRequest) {
        return ResponseEntity.ok(employeeService.updatePassword(employeeRequest));
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeResponse>> getEmployees(@RequestHeader(name="Authorization") String authToken) {
        try {
        String token = authToken.split(" ")[1].trim();
        Long id = jwtHelper.extractUserId(token);
        return ResponseEntity.ok(employeeService.getAllEmployees(id));}
        catch( Exception e){
            throw new ResourceNotFoundException("No employees found or invalid token.");
        }
    }

    @PostMapping()
    public ResponseEntity<String> addSalary(@RequestBody EmployeeSalaryRequest employeeSalaryRequest) {
        try {
            // Add salary entry for each employee in the list
            String result = employeeService.addEmployeeSalary(employeeSalaryRequest.employeeIds());
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            throw new BadRequestException("Error adding salary: " + e.getMessage());
        }
    }


    @PutMapping()
    public ResponseEntity<String> updateSalary(@RequestBody UpdateSalaryRequest updateSalaryRequest) {
        try {
            employeeService.updateEmployeeSalary(
                    updateSalaryRequest.employeeId(),
                    updateSalaryRequest.newSalary()
            );
            return ResponseEntity.status(HttpStatus.OK).body("Employee salary updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating salary: " + e.getMessage());
        }
    }


}
