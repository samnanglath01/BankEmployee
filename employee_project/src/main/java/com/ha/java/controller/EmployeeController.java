package com.ha.java.controller;

import com.ha.java.dto.Employee;
import com.ha.java.dto.EmployeeDetailsPageable;
import com.ha.java.entity.EmployeeEntity;
import com.ha.java.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    private String folder = "D:\\Anime wallpaper\\imagePath\\";

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping("employees")
    public ResponseEntity<EmployeeEntity> createEmployee(@RequestParam Map<String, String> allFields,
                                                         @RequestParam("file") MultipartFile file) throws IOException {

        String imagePath = folder + file.getOriginalFilename();

        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .firstName(allFields.get("firstName"))
                .lastName(allFields.get("lastName"))
                .phoneNumber(allFields.get("phoneNumber"))
                .email(allFields.get("email"))
                .imagePath(imagePath)
                .build();

        file.transferTo(new File(imagePath));

        return ResponseEntity.ok(employeeService.createEmployee(employeeEntity));
    }

    @DeleteMapping("employees/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id) {
        boolean deleted = false;
        deleted = employeeService.deleteEmployee(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }

    @GetMapping("employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {

        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,
                                                   @RequestBody Employee employee) {
        employee = employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok(employee);
    }


    @GetMapping("employees/search")
    public ResponseEntity<EmployeeDetailsPageable> findByAny(@RequestParam(value = "search", defaultValue = "") String anyField,
                                                             @RequestParam(value = "sortBy", defaultValue = "id") String sortBy ,
                                                             @RequestParam(value = "direction", defaultValue = "asc") String direction ,
                                                             @RequestParam(value = "page", defaultValue = "0") int page,
                                                             @RequestParam(value = "size", defaultValue = "5") int size) {
        EmployeeDetailsPageable employees = employeeService
                .findByAny(anyField, sortBy, direction,page, size);
        return ResponseEntity.ok(employees);
    }


}
