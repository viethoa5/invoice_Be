package com.example.invoice.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServer userServer;
    private User getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user;
    }
    @GetMapping
    public List<UserReturnType> getAllUser() {
        return  userServer.getAllUser();
    }
    @GetMapping(value = "/{Id}")
    public UserReturnType getUserDetail(@PathVariable Integer Id) {
        return  userServer.getUserDetail(Id);
    }
    @PutMapping(value = "/password/{Id}")
    public void updateUserPassword(@ModelAttribute("currentPassword") String currentPassword, @ModelAttribute("newPassword") String newPassword) {
        userServer.updateUserPassword(currentPassword, newPassword, getUserInfo());
    }
    @PutMapping(value = "/{Id}")
    public void updateUser(@ModelAttribute("fullName") String fullName, @ModelAttribute("position") String position, @ModelAttribute("consultant") String consultant) {
        User user = getUserInfo();
        userServer.updateUser(fullName, position, consultant, user.getUserId());
    }
    @DeleteMapping(value = "/{Id}")
    public void deleteUser(@PathVariable Integer Id) {
       userServer.deleteUser(Id);
    }
}
