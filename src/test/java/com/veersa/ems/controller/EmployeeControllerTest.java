package com.veersa.ems.controller;

import com.veersa.ems.model.Employee;
import com.veersa.ems.model.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void givenEmployees_GetEmployeesEndpoint_ShouldReturnEmployeeList()
    {
        String baseUrl = "http://localhost:" +port+ "/employees/get-employees";
        ResponseEntity<Employee[]> response = restTemplate.getForEntity(baseUrl, Employee[].class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertTrue(response.getBody().length>0);

    }

    @Test
    void testValidEmailFormat() {

        String baseUrl = "http://localhost:" +port+ "/employees/add-employee";
        // Arrange
        Employee employee = new Employee();
        employee.setFname("testFname");
        employee.setLname("setLname");
        employee.setEmail("john.doeTest@testmail.com");
        employee.setEid(0);
        employee.setHdate("test");

        Location location = new Location();
        location.setLid(3);

        employee.setLocation(location);

        ResponseEntity<Employee> response = restTemplate.postForEntity(baseUrl,employee, Employee.class);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertNotNull(response.getBody());
        System.out.println(response.getBody());


        // Assert
        assertNotNull(employee);
    }

    @Test
    void testInvalidEmailFormat() {
        String baseUrl = "http://localhost:" +port+ "/employees/add-employee";
        // Arrange
        Employee employee = new Employee();
        employee.setFname("testFnameInvalidmail");
        employee.setLname("setLnameInvalidmail");
        employee.setEmail("john.doeTest1.testmail.com");
        employee.setEid(-1);
        employee.setHdate("test");

        Location location = new Location();
        location.setLid(3);

        employee.setLocation(location);

        ResponseEntity<Employee> response = restTemplate.postForEntity(baseUrl,employee, Employee.class);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertNotNull(response.getBody());
        System.out.println(response.getBody());


        // Assert
        assertNotNull(employee);
    }

}