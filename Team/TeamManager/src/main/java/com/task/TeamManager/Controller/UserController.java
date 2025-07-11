package com.task.TeamManager.Controller;

import com.task.TeamManager.Model.Users;
import com.task.TeamManager.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService UserService;

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = UserService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}/roles")
    @PreAuthorize("hasRole('PROJECT_MANAGER')")
    public ResponseEntity<?> updateUserRoles(@PathVariable Long id, @RequestBody Map<String, Set<String>> rolesMap) {
        try {
            Set<String> roles = rolesMap.get("roles");
            Users updatedUser = UserService.updateUserRoles(id, roles);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating roles: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROJECT_MANAGER')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            UserService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting user: " + e.getMessage());
        }
    }

}
