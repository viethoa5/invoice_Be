package com.example.invoice.admin;

import com.example.invoice.discount.Discount;
import com.example.invoice.discount.DiscountReposity;
import com.example.invoice.program.Program;
import com.example.invoice.program.ProgramReposity;
import com.example.invoice.user.User;
import com.example.invoice.user.UserReposity;
import com.example.invoice.user.UserReturnType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AdminServer {
    private final UserReposity userReposity;
    private final ProgramReposity programReposity;
    private final DiscountReposity discountReposity;
    public void insertBatchDiscount(ArrayList<Discount> discounts) {
            discountReposity.saveAll(discounts);
    }

    public void updateDiscount(String discountName, Integer discountValue, int id) {
            Optional<Discount> myDiscount = discountReposity.findById(id);
            if (myDiscount.isPresent()) {
                Discount updatedDiscount = myDiscount.get();
                updatedDiscount.setDiscountName(discountName);
                updatedDiscount.setDiscountValue(discountValue);
                discountReposity.save(updatedDiscount);
            }
    }

    public void deleteDiscount(Integer id) {
            discountReposity.deleteById(id);
    }
    public void insertBatchProgram(ArrayList<Program> programs) {
        programReposity.saveAll(programs);
    }

    public void updateProgram(String programName, Integer programDuration, int id) {
        Optional<Program> myProgram = programReposity.findById(id);
        if (myProgram.isPresent()) {
            Program updatedProgram = myProgram.get();
            updatedProgram.setProgramName(programName);
            updatedProgram.setProgramLast(programDuration);
            programReposity.save(updatedProgram);
        }
    }
    public void deleteProgram(Integer id) {
        programReposity.deleteById(id);
    }
    public List<UserReturnType> getUserFromStatus(String status) {
        if (status.toLowerCase() == "active") {
            Optional<List<UserReturnType>> usersList = userReposity.findAllActiveUser();
            if (usersList.isPresent()) {
                return usersList.get();
            }
        } else {
            if (status.toLowerCase() == "deactive") {
                Optional<List<UserReturnType>> usersList = userReposity.findAllDeActiveUser();
                if (usersList.isPresent()) {
                    return usersList.get();
                }
            } else {
                if (status.toLowerCase() == "noneadmin") {
                    Optional<List<UserReturnType>> usersList = userReposity.findAllNonAdminUser();
                    if (usersList.isPresent()) {
                        return usersList.get();
                    }
                } else {
                    Optional<List<UserReturnType>> usersList = userReposity.findAlLUser();
                    if (usersList.isPresent()) {
                        return usersList.get();
                    }
                }
            }
        }
        return null;
    }
    public void activeUser(Integer id) {
        Optional<User> user = userReposity.findById(id);
        if (user.isPresent()) {
            User updateUser = user.get();
            updateUser.setActive(true);
            userReposity.save(updateUser);
        }
    }


    public void grantUser(Integer id) {
        Optional<User> user = userReposity.findById(id);
        if (user.isPresent()) {
            User updateUser = user.get();
            updateUser.setAdmin(true);
            userReposity.save(updateUser);
        }
    }
    public void deleteUser(Integer id) {
        userReposity.deleteById(id);
    }
}
