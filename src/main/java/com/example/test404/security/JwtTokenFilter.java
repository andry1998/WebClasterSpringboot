package com.example.test404.security;

import com.example.test404.exception.JwtAuthException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);

        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (JwtAuthException e){
            ((HttpServletResponse) servletResponse).sendError(e.getHttpStatus().value());
            throw new JwtAuthException("JWT token is expired or invalide");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
//        String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);
//
//        try {
//            if (token != null && jwtTokenProvider.validateToken(token)) {
//                Authentication authentication = jwtTokenProvider.getAuthentication(token);
//                if (authentication != null) {
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                }
//            }
//        } catch (JwtAuthException e){
//            ((HttpServletResponse) servletResponse).sendError(e.getHttpStatus().value());
//            throw new JwtAuthException("JWT token is expired or invalide");
//        }
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
}
