package com.rout.usermgmt.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** @author sagar@sagarrout.com */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  private final CustomUserDetailsService customUserDetailsService;
  private final SecurityUtil securityUtil;

  public JwtRequestFilter(
      CustomUserDetailsService customUserDetailsService, SecurityUtil securityUtil) {
    this.customUserDetailsService = customUserDetailsService;
    this.securityUtil = securityUtil;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      FilterChain filterChain)
      throws ServletException, IOException {
    final String authorization = httpServletRequest.getHeader("Authorization");

    String username = null;
    String token = null;

    if (authorization != null && authorization.startsWith("Bearer ")) {
      token = authorization.substring(7);
      username = securityUtil.extractUsername(token);
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      final UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);

      if (securityUtil.validateToken(token, userDetails)) {
        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
            new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        usernamePasswordAuthenticationToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    }

    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}
