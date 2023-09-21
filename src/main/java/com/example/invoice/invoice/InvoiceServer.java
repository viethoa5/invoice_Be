package com.example.invoice.invoice;

import com.example.invoice.discount.Discount;
import com.example.invoice.discount.DiscountReposity;
import com.example.invoice.program.Program;
import com.example.invoice.program.ProgramReposity;
import com.example.invoice.user.User;
import com.example.invoice.user.UserReposity;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Transactional
@Service
@RequiredArgsConstructor
public class InvoiceServer {
    private final InvoiceReposity invoiceReposity;
    private final ProgramReposity programReposity;
    private final DiscountReposity discountReposity;
    private final UserReposity userReposity;

    public List<Invoice> getAllInvoice(User user) {
        if (user.isAdmin()) {
            return invoiceReposity.findAll();
        } else {
            List<Invoice> invoices = new ArrayList<Invoice>();
            invoices.addAll(invoiceReposity.findAllByUserId(user.getUserId()).get());
            return invoices;
        }
    }

    public Invoice getInvoiceDetail(int id) {
        Optional<Invoice> invoice = invoiceReposity.findById(id);
        if (invoice.isPresent()) {
            return invoice.get();
        } else {
            throw new IllegalStateException("Invoice not existed");
        }
    }


    public void createInvoice(String students, String airFlight, Date doe, String returnFlight, Date dod, int dayBeforeCamp, int dayAfterCamp, String roomType, Double commission, Date dor, Integer programId, String guardian, boolean evisa, String additionFee, int userId, String consultant, int discountId, boolean isExtraBed, boolean isBaby, double fee, double extendedFee, double paid, double refund) {
        Optional<Program> program = programReposity.findById(programId);
        Optional<User> agent = userReposity.findById(userId);
        Optional<Discount> discount = discountReposity.findById(discountId);
        Invoice invoice = new Invoice(students, airFlight, doe, returnFlight, dod, dayBeforeCamp, dayAfterCamp, roomType, commission, dor,
                guardian, evisa, additionFee, isExtraBed, isBaby, fee, extendedFee, paid, refund, consultant);
        if (agent.isPresent() && program.isPresent()) {
            invoice.setUser(agent.get());
            invoice.setProgram(program.get());
            if (discount.isPresent()) {
                invoice.setDiscount(discount.get());
            }
            invoiceReposity.save(invoice);
        }
    }

    public Invoice updateUserInvoice(int Id, String students, String airFlight, Date doe, String returnFlight, Date dod, int dayBeforeCamp, int dayAfterCamp, String roomType, Double commission, Date dor, Integer programId, String guardian, boolean evisa, String additionFee, int userId,
                                     String consultant, int discountId, boolean isExtraBed, boolean isBaby, double fee, double extendedFee, User user, double paidFee, double refundFee, String status, Date dateOfDecline) {
        if (userId == user.getUserId() || user.isAdmin()) {
            return updateInvoice(Id, students, airFlight, doe, returnFlight, dod,
                    dayBeforeCamp, dayAfterCamp, roomType, commission, dor,
                    programId, guardian, evisa, additionFee, userId, consultant,
                    discountId, isExtraBed, isBaby, fee, extendedFee, paidFee, refundFee, status, dateOfDecline);
        } else {
            return null;
        }
    }

    private Invoice updateInvoice(int Id, String students, String airFlight, Date doe, String returnFlight, Date dod, int dayBeforeCamp, int dayAfterCamp, String roomType, Double commission, Date dor, Integer programId, String guardian, boolean evisa, String additionFee, int userId,
                                  String consultant, int discountId, boolean isExtraBed, boolean isBaby, double fee, double extendedFee,double paidFee, double refundFee, String status, Date dateOfDecline) {
        Optional<Invoice> invoice = invoiceReposity.findById(Id);
        Optional<User> agent = userReposity.findById(userId);
        Optional<Discount> discount = discountReposity.findById(discountId);
        Optional<Program> program = programReposity.findById(programId);
        if (invoice.isPresent() && agent.isPresent()) {
            Invoice myInvoice = invoice.get();
            myInvoice.updateInvoice(students, airFlight, doe, returnFlight, dod,
                    dayBeforeCamp, dayAfterCamp, roomType, commission, dor,
                    guardian, evisa, additionFee,
                    isExtraBed, isBaby, fee, extendedFee, paidFee, refundFee, status, dateOfDecline, consultant);
            myInvoice.setUser(agent.get());
            myInvoice.setProgram(program.get());
            if (discount.isPresent()) {
                myInvoice.setDiscount(discount.get());
            } else {
                myInvoice.setDiscount(null);
            }
            invoiceReposity.save(myInvoice);
            return myInvoice;
        } else {
            throw new IllegalStateException("Invoice not existed");
        }
    }
    public void deleteInvoice(int id, User user) {
        invoiceReposity.deleteInvoiceById(id, user.getUserId());
    }
}
