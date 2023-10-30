package com.veersa.ems.controller;

import com.veersa.ems.model.Location;
import com.veersa.ems.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    LocationService locationService;

    // Get services:

    @GetMapping("get-locations")
    public List<Location> getLocations()
    {
        return locationService.getLocations();
    }

    @GetMapping("get-location")
    public ResponseEntity<List<Location>> getLocation(@RequestBody Location location)
    {
        return locationService.getLocation(location.getCity());
    }

    //Add services
    @PostMapping("add-location")
    public ResponseEntity<Location> addLocation(@RequestBody Location location)
    {
        return locationService.addLocation(location);
    }

    @PostMapping("add-locations")
    public ResponseEntity<List<Location>> addLocations(@RequestBody List<Location> locations)
    {
        return locationService.addLocations(locations);
    }

    //Update services:
    @PutMapping("update-locations")
    public ResponseEntity<List<Location>> updateLocations(@RequestBody Location location)
    {
        return locationService.updateLocations(location);
    }



}
