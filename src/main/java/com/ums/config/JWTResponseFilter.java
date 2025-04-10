package com.ums.config;

import com.ums.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JWTResponseFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    public JWTResponseFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String tokenHeader = request.getHeader("Authorization");
        System.out.println("HEADER: " + tokenHeader);

        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            try {
                String token = tokenHeader.substring(7);
                System.out.println("TOKEN: " + token);

                String userName = jwtService.getUserName(token);
                System.out.println("USERNAME: " + userName);

                // ✅ Add this block 👇
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userName, null, new ArrayList<>());

                SecurityContextHolder.getContext().setAuthentication(authToken);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        filterChain.doFilter(request, response);
    }

}
