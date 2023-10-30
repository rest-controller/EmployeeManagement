package com.veersa.ems.service;

import com.veersa.ems.model.Employee;
import com.veersa.ems.model.Location;
import com.veersa.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public ResponseEntity<Employee> getEmployee(int eid) {
        try {
            System.out.println(eid);
            if (employeeRepository.existsEmployeeByEid(eid)) {
                Employee employee = employeeRepository.findByEid(eid);
                return new ResponseEntity<>(employee, HttpStatus.FOUND);
            } else {
                System.out.println("No employee fetched");
                return new ResponseEntity<>(null, HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("employee not found or exception has occurred");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<Employee> addEmployee(Employee employee) {
        try {
            employeeRepository.save(employee);
            return new ResponseEntity<>(employee, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error in adding a employee");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Employee>> addEmployees(List<Employee> employees) {
        try {
            employeeRepository.saveAll(employees);
            return new ResponseEntity<>(employees, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error in adding a employee");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Employee> updateEmployee(Employee employee) {
        try
        {
             employeeRepository.save(employee);
             return new ResponseEntity<>(employee,HttpStatus.ACCEPTED);
        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("employee cant be updated");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
