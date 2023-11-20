package com.veersa.ems.repository;

import com.veersa.ems.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location,Integer> {
    boolean existsLocationByCity(String city);

    List<Location> findAllByCity(String city);

    List<Location> findAllByCityAndIsDeleted(String city, int i);

    boolean existsLocationByCityAndIsDeleted(String city, int i);

    Location findByCity(String city);
}
