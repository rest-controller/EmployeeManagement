package com.veersa.ems.controller;

import com.veersa.ems.model.Employee;
import com.veersa.ems.model.Location;
import com.veersa.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    // Get services:

    @GetMapping("get-employees")
    public List<Employee> getLocations()
    {
        return employeeService.getEmployees();
    }

    @GetMapping("get-employee")
    public ResponseEntity<Employee> getLocation(@RequestBody Employee employee)
    {
        return employeeService.getEmployee(employee.getEid());
    }

    @PostMapping("add-employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee)
    {
        return employeeService.addEmployee(employee);
    }

    @PostMapping("add-employees")
    public ResponseEntity<List<Employee>> addEmployees(@RequestBody List<Employee> employees)
    {
        EmployeeController locationService;
        return employeeService.addEmployees(employees);
    }

    //Update services:
    @PutMapping("update-employee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee)
    {
        return employeeService.updateEmployee(employee);
    }
}
