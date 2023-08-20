package com.balram.development.controller;

import com.balram.development.entity.Employee;
import com.balram.development.entity.SupplierItemIdentifier;
import com.balram.development.exceptions.EmployeeNotFoundException;
import com.balram.development.repo.EmployeeRepository;
import com.balram.development.repo.PerformanceRepo;
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
  private PerformanceRepo performanceRepo;
  
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

  @GetMapping(value = "/nonp")
  public boolean nonp(){
    Instant start = Instant.now();
    boolean status = employeeLoop();
    Instant end = Instant.now();
    log.info("Ended in seconds " + Duration.between(start, end).getSeconds());
    return status;
  }

  @GetMapping(value = "/withp")
  public boolean withp(){
    Instant start = Instant.now();
    boolean status = performanceLoop();
    Instant end = Instant.now();
    log.info("Ended in seconds " + Duration.between(start, end).getSeconds());
    return status;
  }

  @GetMapping(value = "/supplier")
  public List<SupplierItemIdentifier> getAllSupplier(){
    return performanceRepo.findAll();
  }

  private boolean employeeLoop(){
    try {
      for(int i=0; i < 2000; i++) {
          System.out.println("Preloading " + employeeRepository.save(new Employee("Examine", "Developer" + i)));
        }
    }catch (Exception e){
      log.error("Exception Thrown - ", e);
    }
    return true;
  }

  private boolean performanceLoop(){
    try {
      for(int i=0; i < 2000; i++) {
        System.out.println(" Performance : "+ performanceRepo.save(new SupplierItemIdentifier("buType"+i,"b"+i,"c"+i,"d"+i,"e"+i)));
      }
    }catch (Exception e){
      log.error("Exception Thrown - ", e);
    }
    return true;
  }
}