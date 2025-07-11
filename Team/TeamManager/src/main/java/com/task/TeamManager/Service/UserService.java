package com.task.TeamManager.Service;


import com.task.TeamManager.Model.Roles;
import com.task.TeamManager.Model.Users;
import com.task.TeamManager.Repository.RolesRepository;
import com.task.TeamManager.Repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserService {
    private UserRepository usersRepository;
    private RolesRepository rolesRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository usersRepository,
                       RolesRepository rolesRepository,
                       PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public Users registerUser(Users user) {
        if (usersRepository.existsByUsername(user.getname())) {
            throw new RuntimeException("Username already taken");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Optional<Roles> defaultRole = rolesRepository.findbyname(Roles.ERole.ROLE_TEAM_MEMBER);
        if (defaultRole.isEmpty()) {
            throw new RuntimeException("Default role not found");
        }

        user.setRoles(Collections.singleton(defaultRole.get()));
        return usersRepository.save(user);
    }

    public Optional<Users> findUserById(Long id){
        return usersRepository.findById(id);
    }

}