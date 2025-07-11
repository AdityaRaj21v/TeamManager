package com.task.TeamManager.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 20)
    public enum ERole{
        ROLE_TEAM_MEMBER,
        ROLE_PROJECT_MEMBER,
    }
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private ERole name;
}

