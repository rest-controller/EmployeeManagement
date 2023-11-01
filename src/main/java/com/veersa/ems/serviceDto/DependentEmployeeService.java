package com.veersa.ems.serviceDto;

import com.veersa.ems.dto.DependentEmployeeDTO;
import com.veersa.ems.model.Dependent;
import com.veersa.ems.repository.DependentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DependentEmployeeService {
    @Autowired
    DependentRepository dependentRepository;
    public List<DependentEmployeeDTO> getDependents() {

        return dependentRepository.findAll()
                .stream()
                .map(this::convertModelDto)
                .collect(Collectors.toList());
    }

    private DependentEmployeeDTO convertModelDto(Dependent dependent) {

        DependentEmployeeDTO dependentEmployeeDTO = new DependentEmployeeDTO();
        dependentEmployeeDTO.setDid(dependent.getDid());
        dependentEmployeeDTO.setDfname(dependent.getDfname());
        dependentEmployeeDTO.setDlname(dependent.getDlname());
        dependentEmployeeDTO.setRelationship(dependent.getRelationship());
        dependentEmployeeDTO.setFname(dependent.getEmployee().getFname());
        dependentEmployeeDTO.setLname(dependent.getEmployee().getLname());

        return dependentEmployeeDTO;

    }

    public DependentEmployeeDTO getDependent(int did) {
        Dependent dependent = dependentRepository.findByDid(did);
        DependentEmployeeDTO dependentEmployeeDTO = convertModelDto(dependent);
        return dependentEmployeeDTO;
    }
}
