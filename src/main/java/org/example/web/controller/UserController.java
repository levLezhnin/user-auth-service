package org.example.web.controller;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.example.domain.Role;
import org.example.domain.User;
import org.example.service.RoleService;
import org.example.service.UserService;
import org.example.web.dto.RoleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final Gson gson;

    @GetMapping(value = "/users/getRoles/{userId}")
    public ResponseEntity<?> getRoles(@PathVariable long userId) {
        try {
            List<Role> userRoles = roleService.getUserRoles(userId);
            return ResponseEntity.ok(userRoles.stream().map(RoleDTO::toDTO).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(value = "/admin/users/assign", consumes = "application/json")
    public ResponseEntity<?> assignNewRole(@RequestBody String json) {
        Properties properties = gson.fromJson(json, Properties.class);
        long userId = Long.parseLong(properties.getProperty("userId"));
        String roleName = properties.getProperty("roleName");
        try {
            userService.assignNewRole(userId, roleName);
            return ResponseEntity.ok("Assigned new role: '" + roleName + "' to user with id: " + userId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/admin/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/info")
    public String userData(Principal principal) {
        return principal.getName();
    }

    @GetMapping("/users/vip")
    public String vipZone() {
        return "I'm so VIP";
    }
}
