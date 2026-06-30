package com.sarthak.empmanagement.repository;

import com.sarthak.empmanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA generates the implementation of this interface at runtime.
 * JpaRepository<Employee, Long> already gives us: save(), findById(), findAll(),
 * deleteById(), existsById(), count() etc. - no SQL needed.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
