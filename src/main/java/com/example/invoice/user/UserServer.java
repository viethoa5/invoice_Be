package com.example.invoice.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServer {
    private final UserReposity userReposity;
    private final PasswordEncoder passwordEncoder;
    public List<UserReturnType> getAllUser() {
        Optional<List<UserReturnType>> usersList = userReposity.findAllActiveUser();;
        if (usersList.isPresent()) {
            return usersList.get();
        }
        return null;
    }

    public void updateUser(String fullName, String position, String consultant, Integer id) {
        Optional<User> user = userReposity.findById(id);
        if (user.isPresent()) {
            user.get().setPosition(position);
            user.get().setFullName(fullName);
            user.get().setConsultant(consultant);
            userReposity.save(user.get());
        } else {
            throw new IllegalStateException("user not existed");
        }
    }

    public void deleteUser(Integer id) {
        userReposity.deleteById(id);
    }

    public UserReturnType getUserDetail(int id) {
        Optional<User> user = userReposity.findById(id);
        if (user.isPresent()) {
            return new UserReturnType(user.get().getFullName(), user.get().getGmail(), user.get().getPosition(), id, user.get().getConsultant(), user.get().isAdmin(), user.get().isActive());
        } else {
            throw new IllegalStateException("user not existed");
        }
    }

    public void updateUserPassword(String currentPassword, String newPassword, User user) {
        if (passwordEncoder.matches(currentPassword, user.getPassword())) {
            String encodePas = passwordEncoder.encode(newPassword);
            user.setUserPassword(encodePas);
            userReposity.save(user);
        }
    }
}
