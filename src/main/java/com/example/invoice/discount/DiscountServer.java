package com.example.invoice.discount;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountServer {
    private final DiscountReposity discountReposity;
    public List<Discount> getAllDiscount() {
        return discountReposity.findAll();
    }
}
