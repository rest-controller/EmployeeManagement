package com.veersa.ems.controller;

import com.veersa.ems.model.Employee;
import com.veersa.ems.model.Location;
import com.veersa.ems.service.EmployeeService;
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

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@Slf4j
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
    public List<Employee> getEmployees()
    {
        log.info("[EmployeeController] [getEmployees [trying to get all the locations]");
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
        log.info("[EmployeeController] [getEmployee] [trying to get a single employee]");
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
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee,@Value("${all.prefix:}") String prefix)
    {
        log.info("[EmployeeController] [addEmployee] [trying to add a single employee]");
        return employeeService.addEmployee(employee,prefix);
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
    public ResponseEntity<List<Employee>> addEmployees(@RequestBody List<Employee> employees,@Value("${all.prefix:}") String prefix)
    {
        log.info("[EmployeeController] [addEmployees] [trying to add multiple employees]");
        EmployeeController locationService;
        return employeeService.addEmployees(employees,prefix);
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
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,@Value("${all.prefix:}") String prefix)
    {
        log.info("[EmployeeController] [updateEmployee] [trying to update all the employees]");
        return employeeService.updateEmployee(employee,prefix);
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
        log.info("[EmployeeController] [deleteEmployee] [trying to delete a single employee]");
        return employeeService.deleteEmployee(employee);
    }
}
