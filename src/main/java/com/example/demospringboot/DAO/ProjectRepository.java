package com.example.demospringboot.DAO;

import com.example.demospringboot.entites.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
