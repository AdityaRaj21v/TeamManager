package com.task.TeamManager.Repository;

import com.task.TeamManager.Model.Projects;
import com.task.TeamManager.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Long> {
    Optional<Roles> findbyname(Roles.ERole name);
    @Query("SELECT FROM Project p JOIN FETCH p.projectManager")
    List<Projects> findAllWithManagers();



}