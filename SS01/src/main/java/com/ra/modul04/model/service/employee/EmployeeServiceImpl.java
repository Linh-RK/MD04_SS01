package com.ra.modul04.model.service.employee;

import com.ra.modul04.model.entity.Employee;
import com.ra.modul04.model.repository.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }


    @Override
    public void delete(Integer id) {
        employeeRepository.deleteById(Long.valueOf(id));
    }

    @Override
    public Employee findById(Integer id) {
        return employeeRepository.findById(Long.valueOf(id)).orElse(null);
    }
}
