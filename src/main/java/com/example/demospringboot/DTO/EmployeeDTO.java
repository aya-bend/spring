package com.example.demospringboot.DTO;


import com.example.demospringboot.entites.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private List<String> skills;
    private List<ProjectDTO> projects;
    private Post post;
}