package com.task.TeamManager.Repository;

import com.task.TeamManager.Model.Projects;
import com.task.TeamManager.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Projects, Long> {
    @Query("SELECT p FROM Projects p JOIN FETCH p.projectManager")
    List<Projects> findAllWithProjectManager();
    List<Projects> findByProjectManager(Users projectManager);
    Projects findByName(String name);
}
