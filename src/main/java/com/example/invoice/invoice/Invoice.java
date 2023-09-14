package com.example.invoice.invoice;

import com.example.invoice.discount.Discount;
import com.example.invoice.program.Program;
import com.example.invoice.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
public class Invoice {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Getter
    @Column(name = "Student")
    private String students;
    @Getter
    @ManyToOne
    @Setter
    @JoinColumn(name = "ProgramId")
    private Program program;
    @Getter
    @Column(name = "Guardian")
    private String guardians;
    @Getter
    @Column(name = "AirFlight")
    private String flight;
    @Getter
    @Column(name = "DateofEntry")
    private Date dateOfEntry;
    @Getter
    @Column(name = "ReturnAirFlight")
    private String returnFlight;
    @Getter
    @Column(name = "DateofDepature")
    private Date dateOfDeparture;
    @Getter
    @Column(name = "Evisa")
    private boolean eVisa;
    @Getter
    @Column(name = "AdtionalFee")
    private String fees;
    @Getter
    @Column(name = "AditionalStaybeforeCamp")
    private Integer dayBeforeCamp;
    @Getter
    @Column(name = "AditionalStayafterCamp")
    private Integer dayAfterCamp;
    @Getter
    @Column(name = "RoomType")
    private String roomType;
    @Getter
    @Column(name = "Commission")
    private double commission;
    @Getter
    @Column(name = "DateofRegistion")
    private Date registration;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "UserId")
    private User user;
    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ConsultantId")
    private User consultant;
    @Getter
    @ManyToOne(cascade = CascadeType.ALL)
    @Setter
    @JoinColumn(name = "discountId")
    private Discount discount;
    @Getter
    @Column(name = "HaveExtraBed")
    private boolean haveExtraBed;
    @Getter
    @Column(name = "HaveBaby")
    private boolean haveBaby;
    @Getter
    @Column(name = "StayPrice")
    private Double roomPrice;
    @Getter
    @Column(name = "ExtendedStay")
    private Double extendedStay;
    @Getter
    @Setter
    private Double paid;
    @Getter
    @Setter
    private String status;
    @Getter
    @Setter
    @Column(name = "decline_days")
    private Date declineDays;
    @Getter
    @Setter
    private Double refund;
    public Invoice(){}
    public Invoice(String students, String airFlight, Date doe, String returnFlight, Date dod, int dayBeforeCamp, int dayAfterCamp, String roomType, Double commission,Date dor, String guardian, boolean evisa, String additionFee, boolean isExtraBed, boolean isBaby, double fee, double extendedFee, double paid, Double refund) {
        this.students = students;
        this.flight = airFlight;
        this.dateOfEntry = doe;
        this.returnFlight = returnFlight;
        this.dateOfDeparture = dod;
        this.dayBeforeCamp = dayBeforeCamp;
        this.dayAfterCamp = dayAfterCamp;
        this.roomType = roomType;
        this.commission = commission;
        this.registration = dor;
        this.guardians = guardian;
        this.eVisa = evisa;
        this.fees = additionFee;
        this.extendedStay = extendedFee;
        this.roomPrice = fee;
        this.haveBaby = isBaby;
        this.haveExtraBed = isExtraBed;
        this.paid = paid;
        this.status = "ACTIVE";
        this.declineDays = null;
        this.refund = refund;
    }
    public void updateInvoice(String students, String airFlight, Date doe, String returnFlight, Date dod, int dayBeforeCamp, int dayAfterCamp, String roomType, Double commission,Date dor, String guardian, boolean evisa,
                              String additionFee, boolean isExtraBed, boolean isBaby, double fee, double extendedFee, double paidFee, double refundFee, String status, Date dateOfDecline) {
        this.students = students;
        this.flight = airFlight;
        this.dateOfEntry = doe;
        this.returnFlight = returnFlight;
        this.dateOfDeparture = dod;
        this.dayBeforeCamp = dayBeforeCamp;
        this.dayAfterCamp = dayAfterCamp;
        this.roomType = roomType;
        this.commission = commission;
        this.registration = dor;
        this.guardians = guardian;
        this.eVisa = evisa;
        this.fees = additionFee;
        this.extendedStay = extendedFee;
        this.roomPrice = fee;
        this.haveBaby = isBaby;
        this.haveExtraBed = isExtraBed;
        this.paid = paidFee;
        this.refund = refundFee;
        this.status = status;
        this.declineDays = dateOfDecline;
    }
}
