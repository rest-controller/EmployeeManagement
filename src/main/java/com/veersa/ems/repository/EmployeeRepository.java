package com.veersa.ems.repository;

import com.veersa.ems.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    boolean existsEmployeeByEid(int eid);

    Employee findByEid(int eid);




        Employee findByEidAndIsDeletedEmployee(int eid, int i);

        boolean existsEmployeeByEidAndIsDeletedEmployee(int eid, int i);

        List<Employee> findByIsDeletedEmployee(int i);

//    boolean existsEmployeeByEidAndIsDeleted(int eid, int i);
//
//    Employee findByEidAndIsDeleted(int eid, int i);
//
//    List<Employee> findByIsDeleted(int i);
}
