package com.example.StudyHub.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.StudyHub.entity.User;
import com.example.StudyHub.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final UserRepository ur;

    public JWTAuthFilter(JWTUtil jwtUtil, UserRepository ur) {
        this.jwtUtil = jwtUtil;
        this.ur = ur;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.startsWith("/auth/")
                || path.startsWith("/public/")
                || path.equals("/")
                || path.equals("/home");
    }

    @Override
protected void doFilterInternal(HttpServletRequest request,
                                HttpServletResponse response,
                                FilterChain filterChain)
        throws ServletException, IOException {

    String token = null;

    // ✅ READ JWT FROM COOKIE
    if (request.getCookies() != null) {
        for (jakarta.servlet.http.Cookie cookie : request.getCookies()) {
            if ("jwt".equals(cookie.getName())) {
                token = cookie.getValue();
                break;
            }
        }
    }

    // 🔑 Do NOT block if token is missing
    if (token != null && jwtUtil.isTokenValid(token)
            && SecurityContextHolder.getContext().getAuthentication() == null) {

        String userName = jwtUtil.getSubject(token);

        User user = ur.findByName(userName);

        if (user != null) {

            List<SimpleGrantedAuthority> authorities =
                    List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userName, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    // 🔥 ALWAYS continue
    filterChain.doFilter(request, response);
}

}
