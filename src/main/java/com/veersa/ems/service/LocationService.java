package com.veersa.ems.service;

import com.veersa.ems.model.Employee;
import com.veersa.ems.model.Location;
import com.veersa.ems.repository.LocationRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LocationService {
    @Autowired
    LocationRepository locationRepository;

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public LocationService(EntityManagerFactory entityManagerFactory)
    {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Transactional
    public List<Location> getLocations() {
        try(Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession())
        {
            String hql = "From Location where isDeleted = 0";
            List<Location> locations = session.createQuery(hql,Location.class).list();
            return locations;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




    public ResponseEntity<Location> addLocation(Location location, String prefix) {

        try {

            location.setCity(prefix+location.getCity());
            location.setState(prefix+location.getState());
            location.setIsDeleted(0);
            locationRepository.save(location);
            return new ResponseEntity<>(location, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error in adding a location");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Location>> getLocation(String city) {
        try(Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            System.out.println(city);
            if (locationRepository.existsLocationByCityAndIsDeleted(city,0)) {

                String hql = "From Location where city = :city and isDeleted = 0";
                List<Location> locations = session.createQuery(hql, Location.class).setParameter("city",city).list();
                return new ResponseEntity<>(locations, HttpStatus.FOUND);
            } else {
                System.out.println("No product fetched");
                return new ResponseEntity<>(null, HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Product not found or exception has occurred");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Location>> addLocations(List<Location> locations, String prefix) {
        try {
            for(Location location:locations)
            {
                location.setCity(prefix+location.getCity());
                location.setState(prefix+location.getState());
            }
            locationRepository.saveAll(locations);
            return new ResponseEntity<>(locations, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error in adding a location");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<List<Location>> updateLocations(Location location,String prefix) {
        try
        {
            location.setCity(prefix+location.getCity());
            List<Location> locations = locationRepository.findAllByCity(location.getCity());
            for (Location location1 : locations) {
                location1.setState(prefix+location.getState());
                locationRepository.save(location1);
            }
            return new ResponseEntity<>(locations,HttpStatus.ACCEPTED);
        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Location cant be updates");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Location> deleteLocation(Location location) {
        try
        {
            Location location1 = locationRepository.findByCity(location.getCity());
            location1.setIsDeleted(1);
            locationRepository.save(location1);
            return new ResponseEntity<>(location,HttpStatus.ACCEPTED);
        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Location cant be deleted");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



}
