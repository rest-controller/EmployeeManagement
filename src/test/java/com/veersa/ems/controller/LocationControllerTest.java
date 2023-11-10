package com.veersa.ems.controller;

import com.veersa.ems.model.Dependent;
import com.veersa.ems.model.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LocationControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void givenLocations_GetLocationsEndpoint_ShouldReturnLocationList()
    {
        String baseUrl = "http://localhost:" +port+ "/location/get-locations";
        ResponseEntity<Location[]> response = restTemplate.getForEntity(baseUrl, Location[].class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertTrue(response.getBody().length>0);

    }

    @Test
    void givenANewLocation_PostLocationsEndpoint_ShouldAddANewProduct()
    {
        String baseUrl = "http://localhost:" +port+ "/location/add-location";

        Location testLocation = new Location();
        testLocation.setState("testState");
        testLocation.setCity("testCity");

        ResponseEntity<Location> response = restTemplate.postForEntity(baseUrl,testLocation, Location.class);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertNotNull(response.getBody());
        System.out.println(response.getBody());

    }



}