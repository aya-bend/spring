package com.example.demospringboot.controllers;


import com.example.demospringboot.DTO.EmployeeDTO;
import com.example.demospringboot.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    @PreAuthorize("hasAnyRole('MANAGER', 'TECH_LEAD')")
    public String getAllEmployees(Model model) {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employeeList";
    }

    @GetMapping("/assign")
    @PreAuthorize("hasRole('MANAGER')")
    public String showAssignForm(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("projects", employeeService.getAllEmployees()); // This should be projectService.getAllProjects()
        return "assignEmployee";
    }

    @PostMapping("/assign")
    @PreAuthorize("hasRole('MANAGER')")
    public String assignProject(@RequestParam Long employeeId, @RequestParam Long projectId, @RequestParam double implication) {
        employeeService.assignProject(employeeId, projectId, implication);
        return "redirect:/employees";
    }

    @PostMapping("/employees")
    @PreAuthorize("hasRole('TECH_LEAD')")
    public String addEmployee(@ModelAttribute EmployeeDTO employeeDTO) {
        employeeService.addEmployee(employeeDTO);
        return "redirect:/employees";
    }

    @PostMapping("/employees/remove")
    @PreAuthorize("hasRole('TECH_LEAD')")
    public String removeEmployee(@RequestParam Long id) {
        employeeService.removeEmployee(id);
        return "redirect:/employees";
    }
}