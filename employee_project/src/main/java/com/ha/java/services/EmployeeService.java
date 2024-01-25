package com.ha.java.services;

import com.ha.java.dto.Employee;
import com.ha.java.dto.EmployeeDetailsPageable;
import com.ha.java.entity.EmployeeEntity;

public interface EmployeeService {
    EmployeeEntity createEmployee(EmployeeEntity employee);


    boolean deleteEmployee(Long id);

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Long id, Employee employee);

    //search
    EmployeeDetailsPageable findByAny(String anyField, String sortBy, String direction, int page, int size);

}
