package com.veersa.ems.service;

import com.veersa.ems.model.Employee;
import com.veersa.ems.model.Location;
import com.veersa.ems.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Slf4j

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getEmployees() {

        return employeeRepository.findByIsDeletedEmployee(0);
    }

    public ResponseEntity<Employee> getEmployee(int eid) {
        log.info("[EmployeeService] [getEmployee] [got the eid of the employee]");

        try {
            log.info("[EmployeeService] [getEmployee] [trying to get the employee of the eid]");

            System.out.println(eid);
            if (employeeRepository.existsEmployeeByEidAndIsDeletedEmployee(eid,0)) {
                log.info("[EmployeeService] [getEmployee] [The Employee is present]");

                Employee employee = employeeRepository.findByEidAndIsDeletedEmployee(eid,0);
                log.info("[EmployeeService] [getEmployee] [The Employee is fetched]");

                return new ResponseEntity<>(employee, HttpStatus.FOUND);
            } else {
                log.warn(" !! [EmployeeService] [getEmployee] [Employee with such a eid is not found]");
                System.out.println("No employee fetched");
                return new ResponseEntity<>(null, HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
            }
        } catch (Exception e) {
            log.error("[EmployeeService] [getEmployee] [Exception occurred, please check the input or the code]");
            e.printStackTrace();
            System.out.println("employee not found or exception has occurred");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<Employee> addEmployee(Employee employee, String prefix) {
        log.info("[EmployeeService] [addEmployee] [Employee details recieved]");
        try {
            if(isValidEmail(employee.getEmail()) && employee.getFname()!=null) {
                log.info("[EmployeeService] [addEmployee] [Email is validated and Employee details modifying ... ]");

                employee.setFname(prefix+employee.getFname());
                employee.setLname(prefix+employee.getLname());
                employeeRepository.save(employee);
                log.info("[EmployeeService] [addEmployee] [Employee saved]");

                return new ResponseEntity<>(employee, HttpStatus.CREATED);
            }
            else {
                log.warn("!! [EmployeeService] [addEmployee] [Email not valid !]");
                throw new IllegalArgumentException("Invalid email format or error");
            }
        } catch (Exception e) {
            log.error("!!! [EmployeeService] [addEmployee] [Exception occurred, please check the input or the code.]");
            System.out.println("Error in adding a employee");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Employee>> addEmployees(List<Employee> employees, String prefix) {
        try {
            log.info("[EmployeeService] [addEmployees] [Employees Received]");

            for(Employee employee:employees)
            {
                employee.setFname(prefix+employee.getFname());
                employee.setLname(prefix+employee.getLname());
            }
            employeeRepository.saveAll(employees);
            log.info("[EmployeeService] [addEmployees] [Employees modified and saved]");

            return new ResponseEntity<>(employees, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("[EmployeeService] [addEmployees] [!!! Exception Occurred, Please check input or the code]");

            System.out.println("Error in adding a employee");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Employee> updateEmployee(Employee employee, String prefix) {
        try
        {
            log.info("[EmployeeService] [updateEmployee] [new Employee details recieved]");
            employee.setFname(prefix+employee.getFname());
            employee.setLname(prefix+employee.getLname());
            log.info("[EmployeeService] [updateEmployee] [new Employee details saved]");
             employeeRepository.save(employee);
             return new ResponseEntity<>(employee,HttpStatus.ACCEPTED);
        }catch (Exception e)
        {
            log.error("!!! [EmployeeService] [updateEmployee] [Exception occurred, please check the input ot the code]");
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
        log.info(" [EmployeeService][deleteeEmployee][recieved the employee whose id has to be deleted]");
        try
        {
            Employee employee1 = employeeRepository.findByEid(employee.getEid());
            log.info("[EmployeeService][deleteEmployee][the employee is found]");
            employee1.setIsDeletedEmployee(1);
            log.info("[EmployeeService][deleteEmployee][the employee is deleted]");

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
