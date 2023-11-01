package com.veersa.ems.serviceDto;

import com.veersa.ems.dto.EmployeeLocationDTO;
import com.veersa.ems.model.Employee;
import com.veersa.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeLocationService {
    @Autowired
    EmployeeRepository employeeRepository;
    public List<EmployeeLocationDTO> getEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::convertModelDTO)
                .collect(Collectors.toList());

    }

    private EmployeeLocationDTO convertModelDTO(Employee employee) {

        EmployeeLocationDTO employeeLocationDTO = new EmployeeLocationDTO();
        employeeLocationDTO.setEid(employee.getEid());
        employeeLocationDTO.setFname(employee.getFname());
        employeeLocationDTO.setLname(employee.getLname());
        employeeLocationDTO.setState(employee.getLocation().getState());
        employeeLocationDTO.setEmail(employee.getEmail());
        return employeeLocationDTO;
    }

    public EmployeeLocationDTO getEmployee(int eid)
    {
        Employee employee = employeeRepository.findByEid(eid);
        return convertModelDTO(employee);
    }
}
