package com.veersa.ems.repository;

import com.veersa.ems.model.Dependent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DependentRepository extends JpaRepository<Dependent, Integer> {




    boolean existsDependentByDid(int did);

    Dependent findByDid(int did);

    List<Dependent> findAllByIsDeleted(int i);

    boolean existsDependentByDidAndIsDeleted(int did, int i);

    Dependent findByDidAndIsDeleted(int did, int i);
}
