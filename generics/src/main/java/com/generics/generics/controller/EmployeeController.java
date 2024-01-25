package com.generics.generics.controller;

import com.generics.generics.entity.Employee;
import com.generics.generics.service.BaseService;
import com.generics.generics.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService baseService;

    public EmployeeController(EmployeeService baseService) {
        this.baseService = baseService;
    }

    @PostMapping
    public ResponseEntity<Employee> save(@RequestBody Employee employee) {
        return ResponseEntity.ok(baseService.save(employee));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok(baseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable long id) {
        return ResponseEntity.ok(baseService.findById(id));
    }

    @DeleteMapping
    public ResponseEntity<String> delete(Employee employee) {
        return ResponseEntity.ok(baseService.delete(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        return ResponseEntity.ok(baseService.deleteById(id));
    }

}
