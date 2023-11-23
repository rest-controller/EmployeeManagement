package com.veersa.ems.service;

import com.veersa.ems.model.Employee;
import com.veersa.ems.model.Location;
import com.veersa.ems.repository.LocationRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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
        log.info("[LocationService] [getLocations] [getting into the function]");
        try(Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession())
        {
            log.info("[LocationService] [getLocations] [Session created successfully]");
            String hql = "From Location where isDeleted = 0";
            log.info("[LocationService] [getLocations] [hql made]");
            List<Location> locations = session.createQuery(hql,Location.class).list();
            log.info("[LocationService] [getLocations] [locations fetched]");
            return locations;
        }
        catch (Exception e) {
            log.error("!! [LocationService] [getLocations] [There was an error in getting the locations]");
            e.printStackTrace();
            return null;
        }
    }




    public ResponseEntity<Location> addLocation(Location location, String prefix) {

        try {
            log.info("[LocationService] [addLocation] [trying to add the location]");
            location.setCity(prefix+location.getCity());
            location.setState(prefix+location.getState());
            log.info("[LocationService] [addLocation] [city and state changed with prefix]");
            location.setIsDeleted(0);
            locationRepository.save(location);
            log.info("[LocationService] [addLocation] [location is saved]");
            return new ResponseEntity<>(location, HttpStatus.CREATED);
        } catch (Exception e) {

            log.error("[LocationService] [addLocation] [the location was not added due to some error]");
            System.out.println("Error in adding a location");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Location>> getLocation(String city) {
        log.info("[LocationService] [getLocation] [got the city to get the locations]");
        try(Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            log.info("[LocationService] [getLocation] [trying to get the locations]");
            System.out.println(city);
            if (locationRepository.existsLocationByCityAndIsDeleted(city,0)) {
                log.info("[LocationService] [getLocation] [The location is found and its not deleted]");
                String hql = "From Location where city = :city and isDeleted = 0";
                List<Location> locations = session.createQuery(hql, Location.class).setParameter("city",city).list();
                log.info("[LocationService] [getLocation] [The location is fetched]");
                return new ResponseEntity<>(locations, HttpStatus.FOUND);
            } else {
                log.error("[LocationService] [getLocation] [!! The location do not exist]");
                System.out.println("No product fetched");
                return new ResponseEntity<>(null, HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[LocationService] [getLocation] [!!! Exception occurred, please chek the input or code]");
            System.out.println("Product not found or exception has occurred");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Location>> addLocations(List<Location> locations, String prefix) {
        try {
            log.info("[LocationService] [addLocations] [Locations Received]");
            for(Location location:locations)
            {
                location.setCity(prefix+location.getCity());
                location.setState(prefix+location.getState());
            }
            locationRepository.saveAll(locations);
            log.info("[LocationsService] [addLocations] [Locations modified and saved]");
            return new ResponseEntity<>(locations, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("[LocationService] [addLocations] [!!! Exception Occurred, Please check input or the code]");
            System.out.println("Error in adding a location");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<List<Location>> updateLocations(Location location,String prefix) {
        try
        {

            log.info("[LocationService] [updateLocations] [Location info recieved]");
            location.setCity(prefix+location.getCity());
            List<Location> locations = locationRepository.findAllByCity(location.getCity());
            log.info("[LocationService] [updateLocations] [Location with the same city recieved]");
            int totalNumberOfLocations = locations.size();
            log.info("[LocationService] [updateLocations] [same city location - "+totalNumberOfLocations+"]");

            for (Location location1 : locations) {
                location1.setState(prefix+location.getState());
                log.info("[LocationService] [updateLocations] [Location state changed]");
                locationRepository.save(location1);
            }
            log.info("[LocationService] [updateLocations] [All Locations changed]");

            return new ResponseEntity<>(locations,HttpStatus.ACCEPTED);
        }catch (Exception e)
        {
            log.warn("[LocationService] [updateLocations] [!!! Exception occurred, please check the code or the input]");

            e.printStackTrace();
            System.out.println("Location cant be updates");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Location> deleteLocation(Location location) {
        try
        {
            Location location1 = locationRepository.findByCity(location.getCity());
            log.info("[LocationService] [deleteLocations] [Location City recieved]");

            location1.setIsDeleted(1);
            log.info("[LocationService] [deleteLocations] [Location isDeleted is set to 1]");

            locationRepository.save(location1);
            log.info("[LocationService] [deleteLocations] [Location is saved with isDeleted=1]");

            return new ResponseEntity<>(location,HttpStatus.ACCEPTED);
        }catch (Exception e)
        {
            log.info("[LocationService] [deleteLocations] [!!! Exception has occurred, please check the input or the code]");

            e.printStackTrace();
            System.out.println("Location cant be deleted");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



}
