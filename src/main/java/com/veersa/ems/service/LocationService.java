package com.veersa.ems.service;

import com.veersa.ems.model.Location;
import com.veersa.ems.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LocationService {
    @Autowired
    LocationRepository locationRepository;

    public List<Location> getLocations() {
        return locationRepository.findAll();
    }

    public ResponseEntity<Location> addLocation(Location location) {

        try {
            locationRepository.save(location);
            return new ResponseEntity<>(location, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error in adding a location");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Location>> getLocation(String city) {
        try {
            System.out.println(city);
            if (locationRepository.existsLocationByCity(city)) {
                List<Location> locations = locationRepository.findAllByCity(city);
                return new ResponseEntity<>(locations, HttpStatus.FOUND);
            } else {
                System.out.println("No product fetched");
                return new ResponseEntity<>(null, HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Product not found or exception has occurred");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Location>> addLocations(List<Location> locations) {
        try {
            locationRepository.saveAll(locations);
            return new ResponseEntity<>(locations, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error in adding a location");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<List<Location>> updateLocations(Location location) {
        try
        {
            List<Location> locations = locationRepository.findAllByCity(location.getCity());
            for (Location location1 : locations) {
                location1.setState(location.getState());
                locationRepository.save(location1);
            }
            return new ResponseEntity<>(locations,HttpStatus.ACCEPTED);
        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Location cant be updates");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
