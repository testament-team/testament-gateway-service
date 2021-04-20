package dev.testament.gateway.security.services;

import dev.testament.gateway.security.configuration.JwtConfiguration;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private JwtConfiguration jwtConfiguration;

    public JwtAuthorizationFilter(AuthenticationManager authManager, JwtConfiguration jwtConfiguration) {
        super(authManager);
        this.jwtConfiguration = jwtConfiguration;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        String header = req.getHeader(jwtConfiguration.getHeader());
        if (header == null || !header.startsWith(jwtConfiguration.getPrefix())) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);

    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

        String token = request.getHeader(jwtConfiguration.getHeader());

        if (token != null) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(jwtConfiguration.getSecret().getBytes())
                    .parseClaimsJws(token.replace(jwtConfiguration.getPrefix(), ""))
                    .getBody()
                    .getSubject();

            if (user != null)
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());

            return null;

        }

        return null;

    }

}