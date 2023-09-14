package com.example.invoice.discount;

import com.example.invoice.program.Program;
import com.example.invoice.user.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/discounts")
public class DiscountController {
    private final DiscountServer discountServer;
    @GetMapping
    public List<Discount> getAllDiscount() {
        return discountServer.getAllDiscount();
    }
}
