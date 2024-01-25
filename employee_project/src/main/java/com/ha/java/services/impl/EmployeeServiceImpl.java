package com.ha.java.services.impl;

import com.ha.java.entity.EmployeeEntity;
import com.ha.java.dto.Employee;
import com.ha.java.dto.EmployeeDetailsPageable;
import com.ha.java.exception.ResourceNotFoundException;
import com.ha.java.repository.EmployeeRepository;
import com.ha.java.services.EmployeeService;
import com.ha.java.spec.EmployeeSpec;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeEntity createEmployee(EmployeeEntity employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public boolean deleteEmployee(Long id) {
        EmployeeEntity employee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Employee with id %s not found", id)));
        employeeRepository.delete(employee);
        return true;
    }

    @Override
    public Employee getEmployeeById(Long id){
        EmployeeEntity employeeEntity
                = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Employee with id %s not found", id)));

        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntity, employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        EmployeeEntity employeeEntity
                = employeeRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        employeeEntity.setEmail(employee.getEmail());
        employeeEntity.setPhoneNumber(employee.getPhoneNumber());
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());

        employeeRepository.save(employeeEntity);
        return employee;
    }

    @Override
    public EmployeeDetailsPageable findByAny(String field, String sortBy, String direction, int page, int size) {

        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<EmployeeEntity> emEntity = null;
        List<Employee> emPage = null;

        if(field.equals("")){
            emEntity = employeeRepository.findAll(pageable);
            emPage = emEntity.getContent()
                    .stream()
                    .map(em -> {
                        try {
                            return new Employee(em.getId(),
                                    em.getFirstName(),
                                    em.getLastName(),
                                    em.getPhoneNumber(),
                                    em.getEmail(),

                                    Files.readAllBytes(new File(em.getImagePath()).toPath())
                            );
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toList());

            if(emPage.isEmpty()){
                throw new ResourceNotFoundException("No employees found");
            }
        }
        else{
            emEntity = employeeRepository.findAll(EmployeeSpec.findByAny(field), pageable);
            emPage = emEntity.getContent()
                    .stream()
                    .map(em -> {
                        try {
                            return new Employee(em.getId(),
                                    em.getFirstName(),
                                    em.getLastName(),
                                    em.getPhoneNumber(),
                                    em.getEmail(),

                                    Files.readAllBytes(new File(em.getImagePath()).toPath())
                            );
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toList());
            if(emPage.isEmpty()){
                throw new ResourceNotFoundException("No employees found with: " + field);
            }
        }
        return new EmployeeDetailsPageable(emPage, emEntity.getNumber(), emEntity.getSize(), emEntity.getTotalElements(), emEntity.getTotalPages(), emEntity.isLast());

    }


}
