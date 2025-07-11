package com.task.TeamManager.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tasks")
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 200, message = "Title must be between 3 to 200")
    private String title;

    @NotBlank(message = "Description is required")
    @Column(columnDefinition = "Text")
    private String description;

    public String getTitle() {

        return "";
    }

    public Instant getDueDate() {

        return null;
    }

    public void setProject(Projects project) {

    }

    public void setAssignedTo(Users user) {

    }

    @Size(min = 2,max=20)
    //ETaskStatus
    public enum EStatus{
        TO_DO,
        IN_PROGRESS,
        COMPLETED,
        BLOCKED
    }
    private String status;

    //ETaskPriority
    public enum EPriority{
        LOW,MEDIUM,HIGH,URGENT
    }
    private String priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EStatus Status =EStatus.TO_DO;
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EPriority Priority =EPriority.MEDIUM;
    private String projectId;

    private String assignedToId;
    @CreationTimestamp
    private LocalDateTime createdAt;
}