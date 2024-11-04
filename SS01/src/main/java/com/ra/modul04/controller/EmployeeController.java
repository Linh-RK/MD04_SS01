package com.ra.modul04.controller;

import com.ra.modul04.model.entity.Employee;
import com.ra.modul04.model.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String index(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees",employees);
        return "employee/employee";
    }
//    ADD-----------------------------------
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("employee", employee);
            return "/employee/add";
        }
        if(employeeService.save(employee) != null) {
            return "redirect:/employee";
        }
        model.addAttribute("employees", employeeService.findAll());
        return "/employee/add";
    }
//    EDIT-----------------------------------
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        return "employee/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @ModelAttribute Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("employee", employee);
            return "employee/edit";
        }
        if(employeeService.save(employee) != null) {
            return "redirect:/employee";
        }
        model.addAttribute("employees", employeeService.findAll());
        return "employee/edit";
    }

//    DELETE-----------------------------------
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        employeeService.delete(id);
        return "redirect:/employee";
    }



}
