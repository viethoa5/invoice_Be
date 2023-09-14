package com.example.invoice.discount;

import com.example.invoice.program.Program;
import com.example.invoice.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscountServer {
    private final DiscountReposity discountReposity;
    public List<Discount> getAllDiscount() {
        return discountReposity.findAll();
    }
}
