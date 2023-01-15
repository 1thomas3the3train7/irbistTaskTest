package com.example.irbisBlock.jwt;


import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import java.io.IOException;

@Component
public class JwtFilter implements Filter {
    private final JwtUtils jwtUtils;

    public JwtFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final String token = gerBearerToken(request);
        if(token != null && jwtUtils.validateAccessToken(token)){
            final Claims claims = jwtUtils.getAccessClaims(token);
            final JwtAuth jwtAuth = jwtUtils.generate(claims);

            if(jwtAuth.getRoles() != null){
                jwtAuth.setAuthenticated(true);
            }
            SecurityContextHolder.getContext().setAuthentication(jwtAuth);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
    private String gerBearerToken(final HttpServletRequest request){
        final String bearer = request.getHeader("Authorization");
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}
