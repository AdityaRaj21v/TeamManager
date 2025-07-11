package com.task.TeamManager.Repository;

import com.task.TeamManager.Model.Tasks;
import com.task.TeamManager.Model.Tasks.EPriority;
import com.task.TeamManager.Model.Tasks.EStatus;
import com.task.TeamManager.Model.Projects;
import com.task.TeamManager.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Long> {
    List<Tasks> findByAssignedTo(Users user);
    List<Tasks> findByProject(Projects project);
    List<Tasks> findByStatus(EStatus status);
    List<Tasks> findByPriority(EPriority priority);
    @Query("SELECT t FROM Tasks t JOIN FETCH t.projectId JOIN FETCH t.assignedToId")
    List<Tasks> findAllWithProjectAndAssignee();
}