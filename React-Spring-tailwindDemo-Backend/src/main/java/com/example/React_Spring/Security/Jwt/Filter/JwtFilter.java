package com.example.React_Spring.Security.Jwt.Filter;

import com.example.React_Spring.Security.Jwt.JwtServiceClass;

import com.example.React_Spring.Security.SecurityService.BIkeOwnerDetailsServiceClass;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private  JwtServiceClass jwt;
    @Autowired
    ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader=request.getHeader("Authorization");
        String token=null;
        String userName=null;
        if(authHeader!=null&&authHeader.startsWith("Bearer ")){
            token=authHeader.substring(7);
            userName=jwt.extractUsername(token);
        }
        if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userdetails=context.getBean(BIkeOwnerDetailsServiceClass.class).loadUserByUsername(userName);
            if(jwt.validateToken(token,userdetails)){
     UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userdetails,null,userdetails.getAuthorities());
     authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
     SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request,response);
    }
}

//This JwtFilter is a Spring Security filter that runs once for every incoming request and is responsible for JWT-based authorization:
// it reads the Authorization header, checks for a Bearer token, extracts the JWT and retrieves the username from it, and if the user is not already authenticated,
// it loads the user’s details from the database using UserDetailsService, validates the token (signature, username match, and expiration),
// and then creates a UsernamePasswordAuthenticationToken with the user’s authorities and sets it in the SecurityContextHolder,
// which tells Spring Security that the request is authenticated; finally, the filter passes control to the next filter or controller,
// enabling secure, stateless access to protected endpoints.
//