package com.example.invoice.program;

import com.example.invoice.invoice.Invoice;
import jakarta.persistence.*;

import java.util.List;
@Entity
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int programId;
    private String programName;
    private int programLast;
    @OneToMany(mappedBy = "program")
    private List<Invoice> invoices;
    public int getProgramId() {
        return programId;
    }
    public Program() {}
    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public int getProgramLast() {
        return programLast;
    }

    public void setProgramLast(int programLast) {
        this.programLast = programLast;
    }
}
