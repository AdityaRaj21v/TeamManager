package com.task.TeamManager.Service;

import com.task.TeamManager.Model.Projects;
import com.task.TeamManager.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    public Projects createProject(Projects project) {
        return projectRepository.save(project);
    }
    public List<Projects> getAllProjects() {
        return projectRepository.findAll();
    }
    public List<Projects> getProjectsByManager(Long managerId) {
        return projectRepository.findByManagerId(managerId);
    }
    public Optional<Projects> updateProject(Long id, Projects updatedProject) {
        return projectRepository.findById(id).map(project -> {
            project.setName(updatedProject.getName());
            project.setDescription(updatedProject.getDescription());
            project.setManagerId(updatedProject.getManagerId());
            project.setStartDate(updatedProject.getStartDate());
            project.setEndDate(updatedProject.getEndDate());
            return projectRepository.save(project);
        });
    }
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
