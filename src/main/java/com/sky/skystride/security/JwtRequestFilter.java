package com.sky.skystride.security;

import com.sky.skystride.entities.SkyUser;
import com.sky.skystride.dto.ProfileDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import com.sky.skystride.services.SkyUserService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private SkyUserService service;

    private JwtUtil jwtUtil;

    public JwtRequestFilter(){
        this.jwtUtil = new JwtUtil();
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Maybe use US spelling if error is thrown.
        final String authorisationHeader = request.getHeader("Authorization");

        String email;
        String token;

        if (authorisationHeader != null && authorisationHeader.startsWith("Bearer ")){
            token = authorisationHeader.substring(7);
            email = jwtUtil.extractEmail(token);

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null){
                ProfileDTO user = this.service.getProfileByEmail(email);

                if (jwtUtil.validateToken(token, user)){
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, null);
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
