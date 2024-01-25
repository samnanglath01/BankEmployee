package com.generics.generics.service.impl;

import com.generics.generics.entity.Employee;
import com.generics.generics.repository.EmployeeRepository;
import com.generics.generics.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee, Long> implements EmployeeService {

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super(employeeRepository);
    }
}
