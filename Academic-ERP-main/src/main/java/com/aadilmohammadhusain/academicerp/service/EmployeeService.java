package com.aadilmohammadhusain.academicerp.service;

import com.aadilmohammadhusain.academicerp.dto.employee.EmployeeAuthResponse;
import com.aadilmohammadhusain.academicerp.dto.employee.EmployeeRequest;
import com.aadilmohammadhusain.academicerp.dto.employee.EmployeeResponse;
import com.aadilmohammadhusain.academicerp.dto.employee.LoginRequest;
import com.aadilmohammadhusain.academicerp.entity.EmployeeSalary;
import com.aadilmohammadhusain.academicerp.helper.EncryptionService;
import com.aadilmohammadhusain.academicerp.helper.JWTHelper;
import com.aadilmohammadhusain.academicerp.mapper.EmployeesMapper;
import com.aadilmohammadhusain.academicerp.repo.DepartmentsRepo;
import com.aadilmohammadhusain.academicerp.repo.EmployeeRepo;
import com.aadilmohammadhusain.academicerp.entity.Employees;
import com.aadilmohammadhusain.academicerp.repo.EmployeeSalaryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final  EmployeeSalaryRepo employeeSalaryRepo;
    private final DepartmentsRepo departmentsRepo;
    private final EmployeesMapper employeesMapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;

    public String updatePassword(EmployeeRequest request) {
        Optional<Employees> optionalEmployee = employeeRepo.findByEmail(request.email());

        if (optionalEmployee.isPresent()) {
            Employees employee = optionalEmployee.get();
            Employees mapperEntity = employeesMapper.toEntity(request);
            employee.setPassword(encryptionService.encodePassword(mapperEntity.getPassword()));
            employeeRepo.save(employee);
        }
        return "Password updated";
    }

    public EmployeeAuthResponse loginCustomer(LoginRequest request) {
        Optional<Employees> optionalEmployee = employeeRepo.findByEmail(request.email());

        Employees employee;
        if (optionalEmployee.isPresent()) {
            employee = optionalEmployee.get();
            if (encryptionService.verifyPassword(request.password(), employee.getPassword())) {
                if (Objects.equals(employee.getDepartment().getName(), "Accounts")) {
                    return new EmployeeAuthResponse(jwtHelper.generateToken(employee.getEmployeeId()), "Login Successful", 201);
                } else return new EmployeeAuthResponse("null", "Only Accounts Department can access this feature!", 401);
            } else return new EmployeeAuthResponse("null", "Wrong Password!", 401);
        }

        return new EmployeeAuthResponse("null", "Email not found!", 401);
    }

    public List<EmployeeResponse> getAllEmployees(Long id) {
        List<Employees> employeesList = employeeRepo.getAllExcept(id);
        return employeesList.stream()
                .map(employeesMapper::toResponse
                )
                .toList();
    }

    public String addEmployeeSalary(List<Long> employeeIds) {
        try {
            for (Long employeeId : employeeIds) {
                // 1. Find the employee by ID
                Employees employee = employeeRepo.findById(employeeId)
                        .orElseThrow(() -> new RuntimeException("Employee with ID " + employeeId + " not found"));

                // 2. Get the salary from the employee's record
                Double amount = employee.getSalary();

                if (amount == null || amount <= 0) {
                    throw new RuntimeException("Invalid salary for employee with ID " + employeeId);
                }

                EmployeeSalary employeeSalary = new EmployeeSalary();
                employeeSalary.setEmployee(employee);
                employeeSalary.setAmount(amount);  // Use the amount fetched from the employee
                employeeSalary.setDescription("Payment is Successful");  // Static description
                employeeSalary.setPaymentDate(LocalDate.now());  // Set today's date

                employeeSalaryRepo.save(employeeSalary);
            }
            return "Salaries added successfully for all employees.";
        } catch (Exception e) {
            return "Error adding salaries: " + e.getMessage();
        }
    }

    public Employees updateEmployeeSalary(Long employeeId, Double newSalary) {
        // 1. Find the employee by ID
        Employees employee = employeeRepo.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee with ID " + employeeId + " not found."));

        // 2. Update the salary field
        employee.setSalary(newSalary);

        return employeeRepo.save(employee);
    }

}
