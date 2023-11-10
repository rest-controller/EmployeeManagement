package com.veersa.ems.controller;

import com.veersa.ems.model.Dependent;
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
class DependentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void givenDependents_GetDependentEndpoint_ShouldReturnDependentList()
    {
        String baseUrl = "http://localhost:" +port+ "/dependent/get-dependents";
        ResponseEntity<Dependent[]> response = restTemplate.getForEntity(baseUrl, Dependent[].class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertTrue(response.getBody().length>0);

    }

    @Test
    void givenANewDependent_PostDependentEndpoint_ShouldAddANewDependent()
    {
        String baseUrl = "http://localhost:" +port+ "/location/add-location";

        Dependent testDependent = new Dependent();
        testDependent.setDlname("testname");
        testDependent.setDfname("testname");
        testDependent.setRelationship("testRelationship");

        Employee testEmployee = new Employee();
        testEmployee.setEid(0);

        ResponseEntity<Dependent> response = restTemplate.postForEntity(baseUrl,testDependent, Dependent.class);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertNotNull(response.getBody());
        System.out.println(response.getBody());


    }



}