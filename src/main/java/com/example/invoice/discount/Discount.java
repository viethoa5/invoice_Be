package com.example.invoice.discount;

import com.example.invoice.invoice.Invoice;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Entity
public class Discount {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int discountId;
    @Setter
    @Getter
    private String discountName;
    @Getter
    @Setter
    private int discountValue;
    @OneToMany(mappedBy = "discount")
    private List<Invoice> invoices;
    public Discount(int discountId, String discountName, int discountValue) {
        this.discountId = discountId;
        this.discountName = discountName;
        this.discountValue = discountValue;
    }
    public Discount(String discountName, int discountValue) {
        this.discountName = discountName;
        this.discountValue = discountValue;
    }
    public Discount(){}
}
