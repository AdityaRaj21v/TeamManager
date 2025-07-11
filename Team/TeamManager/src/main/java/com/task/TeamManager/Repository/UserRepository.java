package com.task.TeamManager.Repository;

import com.task.TeamManager.Model.Roles.ERole;
import com.task.TeamManager.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
    Optional<Users> findByEmail(String email);
    @Query("SELECT u FROM Users u JOIN u.roles r WHERE r.name = :role")
    List<Users> findByRole(ERole role);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}