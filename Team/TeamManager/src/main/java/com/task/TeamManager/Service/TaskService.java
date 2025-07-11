package com.task.TeamManager.Service;

import com.task.TeamManager.Model.Projects;
import com.task.TeamManager.Model.Tasks;
import com.task.TeamManager.Model.Users;
import com.task.TeamManager.Repository.ProjectRepository;
import com.task.TeamManager.Repository.TaskRepository;
import com.task.TeamManager.Repository.TaskRepository;
import com.task.TeamManager.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.time.LocalDate;

@Service
public class TaskService {


    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public Tasks createTask(Tasks task, Long projectId, Long assignedToId) {
        if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Task title cannot be empty.");
        }

        if (task.getDueDate() != null && task.getDueDate().isBefore(Instant.from(LocalDate.now()))) {
            throw new IllegalArgumentException("Due date cannot be in the past.");
        }

        Projects project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with ID: " + projectId));
        task.setProject(project);

        if (assignedToId != null) {
            Users user = userRepository.findById(assignedToId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + assignedToId));
            task.setAssignedTo(user);
        }

        return taskRepository.save(task);

    }
    public Tasks updateTask(@PathVariable Long id, @RequestBody Tasks task) {
        if(task.getId()!=null) {
            TaskService.SaveTask(task);
        } else {
            throw new IllegalArgumentException("Task ID mismatch");
        }
        task.setId(id);
        task.getTitle().trim();
        if(task.getTitle()!=null){
            task.setTitle(TaskService.isTaskValid(task.getTitle()));
        }else{
            throw new IllegalArgumentException("Task title cannot be empty");
        }
        if(task.getDueDate()!=null){
            TaskService.DuedateValidate(task.getDueDate());
        }else{
            throw new IllegalArgumentException("Due date cannot be null");
        }
        if(task.getAssignedTo()!=null){
            TaskService.AssignedUserValidate(task.getAssignedTo());
        }else{
            throw new IllegalArgumentException("Assigned user cannot be null");
        }
        if(task.getDescription()!=null){
            task.setDescription(TaskService.isDescriptionValid(task.getDescription()));
        }else{
            throw new IllegalArgumentException("Task description cannot be empty");
        }
        if(task.getPriority()!=null){
            TaskService.PriorityValidate(task.getPriority());
        }else{
            throw new IllegalArgumentException("Task priority cannot be null");
        }
        if(task.getStatus()!=null){
            TaskService.StatusValidate(task.getStatus());

        }else{
            throw new IllegalArgumentException("Task Status cannot be null");
        }

        return TaskService.SaveTask(task);
    }
}