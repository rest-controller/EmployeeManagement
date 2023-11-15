package com.veersa.ems.controller;

import com.veersa.ems.model.Location;
import com.veersa.ems.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    public ResponseEntity<Location> addLocation(@RequestBody Location location)
    {
        return locationService.addLocation(location);
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
    public ResponseEntity<List<Location>> addLocations(@RequestBody List<Location> locations)
    {
        return locationService.addLocations(locations);
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
    public ResponseEntity<List<Location>> updateLocations(@RequestBody Location location)
    {
        return locationService.updateLocations(location);
    }



}
