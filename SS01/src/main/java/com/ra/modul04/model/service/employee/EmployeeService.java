package com.ra.modul04.model.service.employee;

import com.ra.modul04.model.entity.Employee;
import com.ra.modul04.model.entity.Product;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee save(Employee employee);
    void delete(Integer id);
    Employee findById(Integer id);
}
