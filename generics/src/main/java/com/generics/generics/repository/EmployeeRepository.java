package com.generics.generics.repository;

import com.generics.generics.entity.Employee;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

@Primary
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
