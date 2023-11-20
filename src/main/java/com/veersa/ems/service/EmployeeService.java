package com.veersa.ems.service;

import com.veersa.ems.model.Employee;
import com.veersa.ems.model.Location;
import com.veersa.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getEmployees() {

        return employeeRepository.findByIsDeletedEmployee(0);
    }

    public ResponseEntity<Employee> getEmployee(int eid) {
        try {
            System.out.println(eid);
            if (employeeRepository.existsEmployeeByEidAndIsDeletedEmployee(eid,0)) {
                Employee employee = employeeRepository.findByEidAndIsDeletedEmployee(eid,0);
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
            if(isValidEmail(employee.getEmail()) && employee.getFname()!=null) {
                employeeRepository.save(employee);
                return new ResponseEntity<>(employee, HttpStatus.CREATED);
            }
            else {
                throw new IllegalArgumentException("Invalid email format or error");
            }
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


    // email verification

    private boolean isValidEmail(String email) {
        // Define a regular expression for a basic email format
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Compile the regex
        Pattern pattern = Pattern.compile(emailRegex);

        // Create a matcher object
        Matcher matcher = pattern.matcher(email);

        // Return whether the email matches the pattern
        return matcher.matches();
    }

    public ResponseEntity<Employee> deleteEmployee(Employee employee) {
        try
        {
            Employee employee1 = employeeRepository.findByEid(employee.getEid());
            employee1.setIsDeletedEmployee(1);
            employeeRepository.save(employee1);
            return new ResponseEntity<>(employee1,HttpStatus.ACCEPTED);
        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("employee cant be deleted");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
