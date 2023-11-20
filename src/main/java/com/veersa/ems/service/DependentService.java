package com.veersa.ems.service;

import com.veersa.ems.model.Dependent;
import com.veersa.ems.model.Location;
import com.veersa.ems.repository.DependentRepository;
import com.veersa.ems.repository.DependentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DependentService {


    @Autowired
    DependentRepository dependentRepository;

    public List<Dependent> getDependents() {
        return dependentRepository.findAllByIsDeleted(0);
    }

    public ResponseEntity<Dependent> getDependent(int did) {
        try {
            System.out.println(did);
            if (dependentRepository.existsDependentByDidAndIsDeleted(did,0)) {
                Dependent dependent = dependentRepository.findByDidAndIsDeleted(did,0);
                return new ResponseEntity<>(dependent, HttpStatus.FOUND);
            } else {
                System.out.println("No Dependent fetched");
                return new ResponseEntity<>(null, HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Dependent not found or exception has occurred");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<Dependent> addDependent(Dependent dependent) {
        try {
            dependentRepository.save(dependent);
            return new ResponseEntity<>(dependent, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error in adding a Dependent");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Dependent>> addDependents(List<Dependent> dependents) {
        try {
            dependentRepository.saveAll(dependents);
            return new ResponseEntity<>(dependents, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error in adding a Dependent");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Dependent> updateDependent(Dependent dependent) {
        try
        {
            dependentRepository.save(dependent);
            return new ResponseEntity<>(dependent,HttpStatus.ACCEPTED);
        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Dependent cant be updated");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Dependent> deleteDependent(Dependent dependent) {
        try
        {
            Dependent dependent1 = dependentRepository.findByDid(dependent.getDid());
            dependent1.setIsDeleted(1);
            dependentRepository.save(dependent1);



            return new ResponseEntity<>(dependent1,HttpStatus.ACCEPTED);
        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Location cant be deleted");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
