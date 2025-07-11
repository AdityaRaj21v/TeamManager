package com.task.TeamManager.Controller;

import com.task.TeamManager.Model.Tasks;
import com.task.TeamManager.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/{id}")
    public ResponseEntity<Tasks> getTaskById(@PathVariable Long id) {
        Optional<Tasks> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<Tasks>> getAllTasks(Pageable pageable) {
        return ResponseEntity.ok(taskService.getAllTasks(pageable));
    }

    @GetMapping("/assigned/{userId}")
    public ResponseEntity<Page<Tasks>> getTasksByAssignedUser(@PathVariable Long userId, Pageable pageable) {
        return ResponseEntity.ok(taskService.getTasksByAssignedUser(userId, pageable));
    }

    @GetMapping("/project/{projectId}/status/{status}")
    public ResponseEntity<Page<Tasks>> getTasksByProjectAndStatus(
            @PathVariable Long projectId,
            @PathVariable String status,
            Pageable pageable) {
        return ResponseEntity.ok(taskService.getTasksByProjectAndStatus(projectId, status, pageable));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<Page<Tasks>> getTasksByStatus(@PathVariable String status, Pageable pageable) {
        return ResponseEntity.ok(taskService.getTasksByStatus(status, pageable));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<Page<Tasks>> getTasksByProject(@PathVariable Long projectId, Pageable pageable) {
        return ResponseEntity.ok(taskService.getTasksByProject(projectId, pageable));
    }

    @GetMapping("/assigned/{userId}/status/{status}")
    public ResponseEntity<Page<Tasks>> getTasksByAssignedUserAndStatus(
            @PathVariable Long userId,
            @PathVariable String status,
            Pageable pageable) {
        return ResponseEntity.ok(taskService.getTasksByAssignedUserAndStatus(userId, status, pageable));
    }

}
