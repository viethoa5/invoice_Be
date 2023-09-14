package com.example.invoice.program;

import com.example.invoice.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProgramServer {
    private final  ProgramReposity programReposity;
    public List<Program> getAllProgram() {
        return programReposity.findAll();
    }
}
