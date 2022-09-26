package com.management.Employee.controller;

import com.management.Employee.exception.ResourceNotFoundException;
import com.management.Employee.model.Employee;
import com.management.Employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins ="http://localhost:3000" )
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeRepository repository;

    @PostMapping("/add")
    public Employee createEmployee(@RequestBody Employee employee){
        return repository.save(employee);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployee(){
        return repository.findAll();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Employee employee = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id :" + id));
        return ResponseEntity.ok(employee);
    }
}
