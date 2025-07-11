package com.task.TeamManager.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import com.task.TeamManager.Model.Roles;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String username;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 120, nullable = false)
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

//    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Roles> roles;

    public String getname() {return username;
    }
}