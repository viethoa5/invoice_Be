package com.example.invoice.program;

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

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/programs")
public class ProgramController {
    private final ProgramServer programServer;
    @GetMapping
    public List<Program> getAllProgram() {
        return programServer.getAllProgram();
    }
}
