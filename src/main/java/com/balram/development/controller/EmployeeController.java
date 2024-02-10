package com.balram.development.controller;

import com.balram.development.entity.Employee;
import com.balram.development.entity.EmployeePerformance;
import com.balram.development.exceptions.EmployeeNotFoundException;
import com.balram.development.repo.EmployeePerformanceRepository;
import com.balram.development.repo.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@RestController
@Slf4j
public class EmployeeController {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private EmployeePerformanceRepository employeePerformanceRepository;
  
  @GetMapping("/employees")
  List<Employee> all() {
    return employeeRepository.findAll();
  }

  @PostMapping("/employees")
  Employee newEmployee(@RequestBody Employee newEmployee) {
    return employeeRepository.save(newEmployee);
  }

  // Single item
  
  @GetMapping("/employees/{id}")
  Employee one(@PathVariable Long id) {
    
    return employeeRepository.findById(id)
      .orElseThrow(() -> new EmployeeNotFoundException(id));
  }

  @PutMapping("/employees/{id}")
  Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
    
    return employeeRepository.findById(id)
      .map(employee -> {
        employee.setName(newEmployee.getName());
        employee.setRole(newEmployee.getRole());
        return employeeRepository.save(employee);
      })
      .orElseGet(() -> {
        newEmployee.setId(id);
        return employeeRepository.save(newEmployee);
      });
  }

  @DeleteMapping("/employees/{id}")
  void deleteEmployee(@PathVariable Long id) {
    employeeRepository.deleteById(id);
  }

  @GetMapping(value = "/serializable")
  public boolean serializable(){
    Instant start = Instant.now();
    boolean status = employeeLoop();
    Instant end = Instant.now();
    log.info("With Serializable - Ended in seconds " + Duration.between(start, end).getSeconds());
    return status;
  }

  @GetMapping(value = "/persistable")
  public boolean persistable(){
    Instant start = Instant.now();
    boolean status = performanceLoop();
    Instant end = Instant.now();
    log.info("With Persistable - Ended in seconds " + Duration.between(start, end).getSeconds());
    return status;
  }

  private boolean employeeLoop(){
    try {
      for(int i=0; i < 20000; i++) {
          log.info("Employee Id - {} ", employeeRepository.save(new Employee("Examine", "Developer" + i)).getId());
        }
    }catch (Exception e){
      log.error("Exception Thrown - ", e);
    }
    return true;
  }

  private boolean performanceLoop(){
    try {
      for(int i=0; i < 20000; i++) {
        log.info("Performance - {} ",employeePerformanceRepository.save(new EmployeePerformance("Examine", "Developer" + i)));
      }
    }catch (Exception e){
      log.warn("Exception Thrown - ", e);
    }
    return true;
  }
}