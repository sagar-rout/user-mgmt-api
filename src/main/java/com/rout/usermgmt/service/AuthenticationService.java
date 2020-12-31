package com.rout.usermgmt.service;

import com.rout.usermgmt.controller.AuthenticationRequest;
import com.rout.usermgmt.controller.AuthenticationResponse;
import com.rout.usermgmt.controller.UnauthorizedException;
import com.rout.usermgmt.security.CustomUserDetailsService;
import com.rout.usermgmt.security.SecurityUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author sagarrout.com
 */
@Service
public class AuthenticationService {

  private final AuthenticationManager authenticationManager;
  private final CustomUserDetailsService customUserDetailsService;
  private final SecurityUtil securityUtil;

  public AuthenticationService(
      AuthenticationManager authenticationManager,
      CustomUserDetailsService customUserDetailsService,
      SecurityUtil securityUtil) {
    this.authenticationManager = authenticationManager;
    this.customUserDetailsService = customUserDetailsService;
    this.securityUtil = securityUtil;
  }

  /**
   * Validate credentials and generate token
   *
   * @param authenticationRequest
   * @return token
   * @throws BadCredentialsException if credentials are wrong.
   */
  public AuthenticationResponse getToken(final AuthenticationRequest authenticationRequest) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              authenticationRequest.getEmailAddress(), authenticationRequest.getPassword()));
    } catch (BadCredentialsException e) {
      throw new UnauthorizedException("Incorrect email address and password");
    }

    final UserDetails userDetails =
        customUserDetailsService.loadUserByUsername(authenticationRequest.getEmailAddress());
    return new AuthenticationResponse(securityUtil.generateToken(userDetails));
  }
}
