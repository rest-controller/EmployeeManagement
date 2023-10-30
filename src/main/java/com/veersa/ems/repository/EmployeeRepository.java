package com.veersa.ems.repository;

import com.veersa.ems.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    boolean existsEmployeeByEid(int eid);

    Employee findByEid(int eid);
}
