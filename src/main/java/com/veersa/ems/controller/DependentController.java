package com.veersa.ems.controller;

import com.veersa.ems.model.Dependent;
import com.veersa.ems.service.DependentService;
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

    @GetMapping("get-dependents")
    public List<Dependent> getDependents()
    {
        return dependentService.getDependents();
    }

    @GetMapping("get-dependent")
    public ResponseEntity<Dependent> getDependent(@RequestBody Dependent dependent)
    {
        return dependentService.getDependent(dependent.getDid());
    }

    @PostMapping("add-dependent")
    public ResponseEntity<Dependent> addDependent(@RequestBody Dependent dependent)
    {
        return dependentService.addDependent(dependent);
    }

    @PostMapping("add-dependents")
    public ResponseEntity<List<Dependent>> addDependents(@RequestBody List<Dependent> dependents)
    {
        return dependentService.addDependents(dependents);
    }

    //Update services:
    @PutMapping("update-dependent")
    public ResponseEntity<Dependent> updateDependent(@RequestBody Dependent dependent)
    {
        return dependentService.updateDependent(dependent);
    }

}
