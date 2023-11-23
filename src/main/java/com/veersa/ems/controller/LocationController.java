package com.veersa.ems.controller;

import com.veersa.ems.model.Location;
import com.veersa.ems.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@Slf4j
@RequestMapping("/location")
public class LocationController {

    @Autowired
    LocationService locationService;

    // Get services:

    @Operation(summary = "Get all Locations", description = "Get a List of Locations", tags = "Location API")
    @ApiResponses(value =
            {
                    @ApiResponse(responseCode = "200",description = "List is fetched successfully",
                    content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Location.class)
                    )}
                    ),

                    @ApiResponse(responseCode = "404", description = "Location not found",
                    content = @Content)
            }
    )
    @GetMapping("get-locations")
    public List<Location> getLocations()
    {
        log.info("[LocationController.java] [getLocations] [trying to get all the locations]");
        return locationService.getLocations();
    }

    @Operation(summary = "Get a Location", description = "Get a List of Locations according to the City provided", tags = "Location API")
    @ApiResponses(value =
            {
                    @ApiResponse(responseCode = "200",description = "List is fetched successfully",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Location.class)
                            )}
                    ),

                    @ApiResponse(responseCode = "404", description = "Location not found",
                            content = @Content)
            }
    )
    @GetMapping("get-location")
    public ResponseEntity<List<Location>> getLocation(@RequestBody Location location)
    {
        log.info("[LocationController.java] [getLocation] [trying to get a single location]");
        return locationService.getLocation(location.getCity());
    }




    //Add services

    @Operation(summary = "Add Location", description = "Add a single Location", tags = "Location API")
    @ApiResponses(value =
            {
                    @ApiResponse(responseCode = "200",description = "Added Successfully",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Location.class)
                            )}
                    ),

                    @ApiResponse(responseCode = "404", description = "Not added",
                            content = @Content)
            }
    )
    @PostMapping("add-location")
    public ResponseEntity<Location> addLocation(@RequestBody Location location, @Value("${all.prefix:}") String prefix)
    {
        log.info("[LocationController.java] [addLocation] [trying to get add a location]");
        return locationService.addLocation(location,prefix);
    }

    @Operation(summary = "Add many Locations", description = "Add many locations in the form of a List", tags = "Location API")
    @ApiResponses(value =
            {
                    @ApiResponse(responseCode = "200",description = "Added Successfully",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Location.class)
                            )}
                    ),

                    @ApiResponse(responseCode = "404", description = "Not added",
                            content = @Content)
            }
    )
    @PostMapping("add-locations")
    public ResponseEntity<List<Location>> addLocations(@RequestBody List<Location> locations,@Value("${all.prefix:}") String prefix)
    {
        log.info("[LocationController.java] [addLocations] [trying to add all the locations]");
        return locationService.addLocations(locations,prefix);
    }

    //Update services:
    @Operation(summary = "Update Location", description = "Update the Location's State and City", tags = "Location API")
    @ApiResponses(value =
            {
                    @ApiResponse(responseCode = "200",description = "Updates Successfully",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Location.class)
                            )}
                    ),

                    @ApiResponse(responseCode = "404", description = "Not Updated",
                            content = @Content)
            }
    )
    @PutMapping("update-locations")
    public ResponseEntity<List<Location>> updateLocations(@RequestBody Location location,@Value("${all.prefix:}") String prefix)
    {
        log.info("[LocationController.java] [updateLocations] [trying to update all the locations]");
        return locationService.updateLocations(location,prefix);
    }




    @Operation(summary = "Deleted Location", description = "Delete Location", tags = "Location API")
    @ApiResponses(value =
            {
                    @ApiResponse(responseCode = "200",description = "Deleted Successfully",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Location.class)
                            )}
                    ),

                    @ApiResponse(responseCode = "404", description = "Deleted",
                            content = @Content)
            }
    )
    @PutMapping("delete-locations")
    public ResponseEntity<Location> deleteLocation(@RequestBody Location location)
    {
        log.info("[LocationController.java] [deleteLocations] [trying to delete  the location]");
        return locationService.deleteLocation(location);
    }





}
