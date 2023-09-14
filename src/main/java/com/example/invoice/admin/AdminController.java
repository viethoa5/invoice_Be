package com.example.invoice.admin;

import com.example.invoice.discount.Discount;
import com.example.invoice.program.Program;
import com.example.invoice.user.User;
import com.example.invoice.user.UserReturnType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/admin")
public class AdminController {
    private final AdminServer adminServer;
    private User getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user;
    }
    @PostMapping(value = "/discounts/create/batch")
    public void insertBatchDiscount(@ModelAttribute("discountList") String discounts, HttpServletResponse response) {
        if (getUserInfo().isAdmin()) {
            Type listType = new TypeToken<ArrayList<Discount>>() {}.getType();
            ArrayList<Discount> discountList = new Gson().fromJson(discounts, listType);
            adminServer.insertBatchDiscount(discountList);
        } else {
            response.setStatus(403);
        }
    }
    private void authenticate(User user, HttpServletResponse response) {
        if (!user.isAdmin()) {
            response.setStatus(403);
        }
    }
    @PutMapping(value = "/discounts/{Id}")
    public void updateDiscount(@ModelAttribute("discountName") String name, @ModelAttribute("discountValue") int value, @PathVariable Integer Id, HttpServletResponse response) {
        authenticate(getUserInfo(), response);
        if (response.getStatus() != 403) {
            adminServer.updateDiscount(name, value, Id);
        }
    }
    @DeleteMapping(value = "/discounts/{Id}")
    public void deleteDiscount(@PathVariable Integer Id, HttpServletResponse response) {
        authenticate(getUserInfo(), response);
        if (response.getStatus() != 403) {
            adminServer.deleteDiscount(Id);
        }
    }
    @PostMapping(value = "/programs/create/batch")
    public void createBatchProgram(@ModelAttribute("programList") String programs, HttpServletResponse response) {
        authenticate(getUserInfo(), response);
        if (response.getStatus() != 403) {
            Type listType = new TypeToken<ArrayList<Program>>() {}.getType();
            ArrayList<Program> programList = new Gson().fromJson(programs, listType);
            adminServer.insertBatchProgram(programList);
        }
    }
    @PutMapping(value = "/programs/{Id}")
    public void updateProgram(@ModelAttribute("programName") String name, @ModelAttribute("programValue") int value, @PathVariable Integer Id, HttpServletResponse response) {
        authenticate(getUserInfo(), response);
        if (response.getStatus() != 403) {
            adminServer.updateProgram(name, value, Id);
        }
    }
    @DeleteMapping(value = "/programs/{Id}")
    public void deleteProgram(@PathVariable Integer Id, HttpServletResponse response) {
        authenticate(getUserInfo(), response);
        if (response.getStatus() != 403) {
            adminServer.deleteProgram(Id);
        }
    }
    @GetMapping(value = "/users")
    public List<UserReturnType> getAllUser(HttpServletResponse response, @RequestParam Optional<String> status) {
        authenticate(getUserInfo(), response);
        if (response.getStatus() != 403) {
            return adminServer.getUserFromStatus(status.get());
        }
     return null;
    }
    @PutMapping(value = "/users/active/{Id}")
    public void activeUser(HttpServletResponse response, @PathVariable int Id) {
        authenticate(getUserInfo(), response);
        if (response.getStatus() != 403) {
            adminServer.activeUser(Id);
        }
    }
    @PutMapping(value = "/users/grant/{Id}")
    public void grantUser(HttpServletResponse response, @PathVariable int Id) {
        authenticate(getUserInfo(), response);
        if (response.getStatus() != 403) {
            adminServer.grantUser(Id);
        }
    }
    @DeleteMapping(value = "/users/{Id}")
    public void deleteUser(HttpServletResponse response, @PathVariable int Id) {
        authenticate(getUserInfo(), response);
        if (response.getStatus() != 403) {
            adminServer.deleteUser(Id);
        }
    }

}
