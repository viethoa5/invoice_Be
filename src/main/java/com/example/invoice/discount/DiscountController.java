package com.example.invoice.discount;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
