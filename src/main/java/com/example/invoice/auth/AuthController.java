package com.example.invoice.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
   private final AuthServer authServer;

    @Value("${spring.refresh.expiration}")
    private Long maxAgeCookie;

    private ResponseEntity<AuthResponseType> authenticationResponse(AuthResponseType authenticationResponse) {
        if (authenticationResponse != null) {
            String accessToken = authenticationResponse.getAccess_Token();
            String refreshToken = authenticationResponse.getRefresh_Token();
            var accessCookie = ResponseCookie.from("access_Token", accessToken).httpOnly(true).maxAge(maxAgeCookie).build();
            var refreshCookie = ResponseCookie.from("refresh_token", refreshToken).httpOnly(true).maxAge(maxAgeCookie).build();
            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, accessCookie.toString())
                    .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                    .body(authenticationResponse);
        } else {
            return  ResponseEntity.status(401).build();
        }
    }

    @PostMapping(value = "/register")
    public void register(@ModelAttribute AuthRegisterType request, HttpServletResponse response) {
        authServer.register(request, response);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponseType> authenticate(@ModelAttribute AuthRequest request) {
        return authenticationResponse(authServer.authenticate(request));
    }

    @PostMapping(value = "/refresh")
    public ResponseEntity<AuthResponseType> refreshToken(HttpServletRequest request,
                                                               HttpServletResponse response,
                                                               @CookieValue("refresh_token") String refreshToken,
                                                               @CookieValue("access_Token") String accessTokenReal) {
        return authenticationResponse(authServer.refresh(request,response,refreshToken,accessTokenReal));
    }
}
