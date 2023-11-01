package com.veersa.ems.repository;

import com.veersa.ems.model.Dependent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DependentRepository extends JpaRepository<Dependent, Integer> {




    boolean existsDependentByDid(int did);

    Dependent findByDid(int did);
}
