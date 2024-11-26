package com.aadilmohammadhusain.academicerp.repo;

import com.aadilmohammadhusain.academicerp.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employees, Long> {
    Optional<Employees> findByEmail(String email);

    @Query("SELECT e FROM Employees e WHERE e.employeeId != :id")
    List<Employees> getAllExcept(@Param("id") Long id);
}