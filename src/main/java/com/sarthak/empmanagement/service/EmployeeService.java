package com.sarthak.empmanagement.service;

import com.sarthak.empmanagement.entity.Department;
import com.sarthak.empmanagement.entity.Employee;
import com.sarthak.empmanagement.exception.ResourceNotFoundException;
import com.sarthak.empmanagement.repository.DepartmentRepository;
import com.sarthak.empmanagement.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer - sits between Controller and Repository.
 * Holds the actual business logic so controllers stay thin.
 */
@Service
@RequiredArgsConstructor // Lombok generates a constructor for final fields -> enables constructor injection
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    public Employee createEmployee(Employee employee) {
        // If the request includes a department id, attach the existing department.
        // (Otherwise department stays null - employee not yet assigned.)
        if (employee.getDepartment() != null && employee.getDepartment().getId() != null) {
            Department department = departmentRepository.findById(employee.getDepartment().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Department not found with id: " + employee.getDepartment().getId()));
            employee.setDepartment(department);
        }
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee existing = getEmployeeById(id);

        existing.setName(updatedEmployee.getName());
        existing.setEmail(updatedEmployee.getEmail());
        existing.setSalary(updatedEmployee.getSalary());

        if (updatedEmployee.getAddress() != null) {
            existing.setAddress(updatedEmployee.getAddress());
        }

        if (updatedEmployee.getDepartment() != null && updatedEmployee.getDepartment().getId() != null) {
            Department department = departmentRepository.findById(updatedEmployee.getDepartment().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Department not found with id: " + updatedEmployee.getDepartment().getId()));
            existing.setDepartment(department);
        }

        return employeeRepository.save(existing);
    }

    public void deleteEmployee(Long id) {
        Employee existing = getEmployeeById(id);
        employeeRepository.delete(existing);
    }
}
