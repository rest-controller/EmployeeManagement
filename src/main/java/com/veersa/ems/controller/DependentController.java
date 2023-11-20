package com.veersa.ems.controller;

import com.veersa.ems.model.Dependent;
import com.veersa.ems.model.Location;
import com.veersa.ems.service.DependentService;
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
@RequestMapping("/dependent")
public class DependentController {
    @Autowired
    DependentService dependentService;

    // Get services:

    @Operation(summary = "Get Dependents", description = "Get the List of dependents", tags = "Dependant API")
    @ApiResponses(value =
            {
                    @ApiResponse(responseCode = "200",description = "Fetched Successfully",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Location.class)
                            )}
                    ),

                    @ApiResponse(responseCode = "404", description = "Not Fetched",
                            content = @Content)
            }
    )
    @GetMapping("get-dependents")
    public List<Dependent> getDependents()
    {
        return dependentService.getDependents();
    }

    @Operation(summary = "Get Dependent", description = "Get A single dependents", tags = "Dependant API")
    @ApiResponses(value =
            {
                    @ApiResponse(responseCode = "200",description = "Fetched Successfully",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Location.class)
                            )}
                    ),

                    @ApiResponse(responseCode = "404", description = "Not Fetched",
                            content = @Content)
            }
    )
    @GetMapping("get-dependent")
    public ResponseEntity<Dependent> getDependent(@RequestBody Dependent dependent)
    {
        return dependentService.getDependent(dependent.getDid());
    }

    @Operation(summary = "Add Dependent", description = "Add a single dependent", tags = "Dependant API")
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
    @PostMapping("add-dependent")
    public ResponseEntity<Dependent> addDependent(@RequestBody Dependent dependent)
    {
        return dependentService.addDependent(dependent);
    }

    @Operation(summary = "Add Many Dependents", description = "Add a List of Dependents", tags = "Dependant API")
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
    @PostMapping("add-dependents")
    public ResponseEntity<List<Dependent>> addDependents(@RequestBody List<Dependent> dependents)
    {
        return dependentService.addDependents(dependents);
    }

    //Update services:
    @Operation(summary = "Update Dependent", description = "Update a dependent", tags = "Dependant API")
    @ApiResponses(value =
            {
                    @ApiResponse(responseCode = "200",description = "Updated Successfully",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Location.class)
                            )}
                    ),

                    @ApiResponse(responseCode = "404", description = "Not Updated",
                            content = @Content)
            }
    )
    @PutMapping("update-dependent")
    public ResponseEntity<Dependent> updateDependent(@RequestBody Dependent dependent)
    {
        return dependentService.updateDependent(dependent);
    }

    @Operation(summary = "Delete Dependent", description = "Delete a dependent", tags = "Dependant API")
    @ApiResponses(value =
            {
                    @ApiResponse(responseCode = "200",description = "Deleted Successfully",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Location.class)
                            )}
                    ),

                    @ApiResponse(responseCode = "404", description = "Not deleted",
                            content = @Content)
            }
    )
    @PutMapping("delete-dependent")
    public ResponseEntity<Dependent> deleteDependent(@RequestBody Dependent dependent)
    {
        return dependentService.deleteDependent(dependent);
    }

}
