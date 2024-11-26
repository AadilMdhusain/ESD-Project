package com.aadilmohammadhusain.academicerp.repo;

import com.aadilmohammadhusain.academicerp.entity.EmployeeSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeSalaryRepo extends JpaRepository<EmployeeSalary, Long> {

}
