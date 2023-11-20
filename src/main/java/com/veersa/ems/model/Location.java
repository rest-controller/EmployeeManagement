package com.veersa.ems.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "location")
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    int lid;

    @Column(name="isDeleted", columnDefinition = "int default 0")
    int isDeleted;

    @Column(name = "city")
    String city;

    @Column(name = "state")
    String state;


}
