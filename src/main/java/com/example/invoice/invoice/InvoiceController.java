package com.example.invoice.invoice;

import com.example.invoice.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/invoices")
public class InvoiceController {
    private final InvoiceServer invoiceServer;

    private User getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user;
    }

    @GetMapping
    public List<Invoice> getAllInvoice() {
        return invoiceServer.getAllInvoice(getUserInfo());
    }

    @GetMapping(value = "/{Id}")
    public Invoice getInvoiceDetail(@PathVariable Integer Id) {
        return invoiceServer.getInvoiceDetail(Id);
    }
    @PostMapping(value = "/create")
    public void createInvoice(@ModelAttribute("students") String students, @ModelAttribute("airFlight") String airFlight, @ModelAttribute("doe") Date doe, @ModelAttribute("returnFlight") String returnFlight, @ModelAttribute("dor") Date dod,
                              @ModelAttribute("dayBeforeCamp") int dayBeforeCamp, @ModelAttribute("dayAfterCamp") int dayAfterCamp, @ModelAttribute("roomType") String roomType, @ModelAttribute("commission") Double commission, @ModelAttribute("dor") Date dor,
                              @ModelAttribute("programId") Integer programId, @ModelAttribute("guardian") String guardian, @ModelAttribute("evisa") boolean evisa, @ModelAttribute("additionFee") String additionFee, @ModelAttribute("userId") int userId, @ModelAttribute("consultant") String consultant,
                              @ModelAttribute("discountId") int discountId, @ModelAttribute("isExtraBed") boolean isExtraBed, @ModelAttribute("isBaby") boolean isBaby, @ModelAttribute("fee") double fee, @ModelAttribute("extendedFee") double extendedFee,
                              @ModelAttribute("paidFee") double paidFee, @ModelAttribute("refundFee") double refundFee) {
              invoiceServer.createInvoice(students, airFlight, doe, returnFlight, dod,
                                          dayBeforeCamp, dayAfterCamp, roomType, commission, dor,
                                          programId, guardian, evisa, additionFee, userId, consultant,
                                          discountId, isExtraBed, isBaby, fee, extendedFee, paidFee, refundFee);
    }
    @PutMapping(value = "/{Id}")
    public Invoice updateInvoice(@PathVariable Integer Id,@ModelAttribute("students") String students, @ModelAttribute("airFlight") String airFlight, @ModelAttribute("doe") Date doe, @ModelAttribute("returnFlight") String returnFlight, @ModelAttribute("dor") Date dod,
                                 @ModelAttribute("dayBeforeCamp") int dayBeforeCamp, @ModelAttribute("dayAfterCamp") int dayAfterCamp, @ModelAttribute("roomType") String roomType, @ModelAttribute("commission") Double commission, @ModelAttribute("dor") Date dor,
                                 @ModelAttribute("programId") Integer programId, @ModelAttribute("guardian") String guardian, @ModelAttribute("evisa") boolean evisa, @ModelAttribute("additionFee") String additionFee, @ModelAttribute("userId") int userId, @ModelAttribute("consultant") String consultant,
                                 @ModelAttribute("discountId") int discountId, @ModelAttribute("isExtraBed") boolean isExtraBed, @ModelAttribute("isBaby") boolean isBaby, @ModelAttribute("fee") double fee, @ModelAttribute("extendedFee") double extendedFee,
                                 @ModelAttribute("paidFee") double paidFee, @ModelAttribute("refundFee") double refundFee, @ModelAttribute("invoiceStatus") String invoiceStatus, @ModelAttribute("dateOfDecline") String dateOfDecline) {
        if (dateOfDecline.isEmpty()) {
            return invoiceServer.updateUserInvoice(Id, students, airFlight, doe, returnFlight, dod,
                    dayBeforeCamp, dayAfterCamp, roomType, commission, dor,
                    programId, guardian, evisa, additionFee, userId, consultant,
                    discountId, isExtraBed, isBaby, fee, extendedFee, getUserInfo(), paidFee, refundFee, invoiceStatus, null);
        } else {
            return invoiceServer.updateUserInvoice(Id, students, airFlight, doe, returnFlight, dod,
                    dayBeforeCamp, dayAfterCamp, roomType, commission, dor,
                    programId, guardian, evisa, additionFee, userId, consultant,
                    discountId, isExtraBed, isBaby, fee, extendedFee, getUserInfo(), paidFee, refundFee, invoiceStatus, Date.valueOf(dateOfDecline));
        }
    }

    @DeleteMapping(value = "/{Id}")
    public void deleteInvoice(@PathVariable Integer Id) {
         invoiceServer.deleteInvoice(Id, getUserInfo());
    }
}
