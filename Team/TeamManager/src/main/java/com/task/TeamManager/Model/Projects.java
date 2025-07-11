package com.task.TeamManager.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "projects")
public class Projects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "project_manager_id")
    private Users projectManager;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "project")
    private List<Tasks> tasks;
}

