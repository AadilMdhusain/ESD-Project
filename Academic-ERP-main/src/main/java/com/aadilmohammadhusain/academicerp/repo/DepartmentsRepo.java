package com.aadilmohammadhusain.academicerp.repo;

import com.aadilmohammadhusain.academicerp.entity.Departments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentsRepo extends JpaRepository<Departments, Long> {
}
