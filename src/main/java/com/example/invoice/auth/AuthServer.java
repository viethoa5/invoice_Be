package com.example.invoice.auth;

import com.example.invoice.config.JwtService;
import com.example.invoice.user.User;
import com.example.invoice.user.UserReposity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServer {
    private final UserReposity userReposity;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void register(AuthRegisterType request, HttpServletResponse response) {
        String encodePassword = passwordEncoder.encode(request.getPassword());
        var user = new User(request.getEmail(), encodePassword, request.getFullName(), request.getPosition(), request.getConsultant());
        userReposity.save(user);
        response.setStatus(200);
    }

    private AuthResponseType resultResponseAuthentication(User user) {
        String jwtAccessToken = jwtService.generateAccessToken(user);
        String jwtRefreshToken = jwtService.generateRefreshToken(user);
        return AuthResponseType.builder()
                .access_Token(jwtAccessToken)
                .refresh_Token(jwtRefreshToken)
                .userId(user.getUserId()).build();
    }

    public AuthResponseType authenticate(AuthRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        Optional<User> user = userReposity.findByGmail(request.getEmail());
        if (user.isPresent()) {
            User currentUser = user.get();
            if (passwordEncoder.matches(request.getPassword(), currentUser.getPassword())) {
                return resultResponseAuthentication(currentUser);
            }
        } else {
            throw new IllegalStateException("user not Active");
        }
        return null;
    }

    public AuthResponseType refresh(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @CookieValue("refresh_token") String refreshToken,
                                    @CookieValue("access_Token") String accessTokenReal)  {
          return null;
    }
}
