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
@Table(name = "employee")

public class Employee implements Serializable {

    @Id
    @Column(name = "employee_id")
    int eid;

    @Column(name = "first_name")
    String fname;

    @Column(name = "last_name")
    String lname;

    @Column(name = "email")
    String email;

    @Column(name = "hire_date")
    String hdate;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

}
