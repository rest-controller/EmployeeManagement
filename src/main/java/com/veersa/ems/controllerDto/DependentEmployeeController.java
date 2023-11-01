package com.veersa.ems.controllerDto;

import com.veersa.ems.dto.DependentEmployeeDTO;
import com.veersa.ems.serviceDto.DependentEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("dependent-employee")
public class DependentEmployeeController {

    @Autowired
    DependentEmployeeService dependentEmployeeService;

    @GetMapping("get-dependents")
    public List<DependentEmployeeDTO> getDependents()
    {
       return dependentEmployeeService.getDependents();
    }
    @GetMapping("get-dependent")
    public DependentEmployeeDTO getDependent(@RequestBody DependentEmployeeDTO dependentEmployeeDTO)
    {
        return dependentEmployeeService.getDependent(dependentEmployeeDTO.getDid());
    }
}
