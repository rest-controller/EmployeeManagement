package com.veersa.ems.controllerDto;

import com.veersa.ems.dto.EmployeeLocationDTO;
import com.veersa.ems.serviceDto.EmployeeLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("employee-location")
public class EmployeeLocationController {
    @Autowired
    EmployeeLocationService employeeLocationService;

    @GetMapping("get-employees")
    public List<EmployeeLocationDTO> getEmployees()
    {
        return employeeLocationService.getEmployees();
    }
    @GetMapping("get-employee")
    public EmployeeLocationDTO getEmployee(@RequestBody EmployeeLocationDTO employeeLocationDTO){
        return employeeLocationService.getEmployee(employeeLocationDTO.getEid());
    }

}
