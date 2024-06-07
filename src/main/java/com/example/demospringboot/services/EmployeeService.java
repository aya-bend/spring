package com.example.demospringboot.services;

import com.example.demospringboot.DAO.EmployeeRepository;
import com.example.demospringboot.DAO.ProjectRepository;
import com.example.demospringboot.DTO.EmployeeDTO;
import com.example.demospringboot.DTO.ProjectDTO;
import com.example.demospringboot.entites.Employee;
import com.example.demospringboot.entites.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(this::convertToDTO).orElse(null);
    }

    public void addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setSkills(employeeDTO.getSkills());
        employee.setPost(employeeDTO.getPost());
        employeeRepository.save(employee);
    }

    public void assignProject(Long employeeId, Long projectId, double implication) {
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
        Optional<Project> projectOpt = projectRepository.findById(projectId);
        if (employeeOpt.isPresent() && projectOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            Project project = projectOpt.get();
            project.setBudget(implication); // Use implication as needed
            employee.getProjects().add(project);
            employeeRepository.save(employee);
        }
    }

    public void removeEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setSkills(employee.getSkills());
        dto.setPost(employee.getPost());
        List<ProjectDTO> projectDTOs = employee.getProjects().stream().map(project -> {
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.setId(project.getId());
            projectDTO.setName(project.getName());
            projectDTO.setBudget(project.getBudget());
            return projectDTO;
        }).collect(Collectors.toList());
        dto.setProjects(projectDTOs);
        return dto;
    }
}