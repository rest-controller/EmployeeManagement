package com.veersa.ems.controller;

import com.veersa.ems.model.Employee;
import com.veersa.ems.model.Location;
import com.veersa.ems.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    // Get services:
    @Operation(summary = "Get all Employees", description = "Get a List of Employees", tags = "Employee API")
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
    @GetMapping("get-employees")
    public List<Employee> getLocations()
    {
        return employeeService.getEmployees();
    }

    @Operation(summary = "Get One Employee", description = "Get Employee according to their E Id", tags = "Employee API")
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
    @GetMapping("get-employee")
    public ResponseEntity<Employee> getLocation(@RequestBody Employee employee)
    {
        return employeeService.getEmployee(employee.getEid());
    }

    @Operation(summary = "Add Employee", description = "Add a Single Employee", tags = "Employee API")
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
    @PostMapping("add-employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee)
    {
        return employeeService.addEmployee(employee);
    }

    @Operation(summary = "Add Employees", description = "Add a List of Employees", tags = "Employee API")
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
    @PostMapping("add-employees")
    public ResponseEntity<List<Employee>> addEmployees(@RequestBody List<Employee> employees)
    {
        EmployeeController locationService;
        return employeeService.addEmployees(employees);
    }

    //Update services:
    @Operation(summary = "Update Employee", description = "Update the employee details", tags = "Employee API")
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
    @PutMapping("update-employee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee)
    {
        return employeeService.updateEmployee(employee);
    }


    @Operation(summary = "Delete Employee", description = "Delete the employee details", tags = "Employee API")
    @ApiResponses(value =
            {
                    @ApiResponse(responseCode = "200",description = "Deleted Successfully",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Location.class)
                            )}
                    ),

                    @ApiResponse(responseCode = "404", description = "Not Deleted",
                            content = @Content)
            }
    )
    @PutMapping("delete-employee")
    public ResponseEntity<Employee> deleteEmployee(@RequestBody Employee employee)
    {
        return employeeService.deleteEmployee(employee);
    }
}
