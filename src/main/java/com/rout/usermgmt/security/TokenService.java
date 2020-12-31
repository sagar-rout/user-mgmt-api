package com.rout.usermgmt.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  public String getEmailAddressFromToken() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
      return ((Authentication) authentication.getDetails()).getName();
    }
    throw new IllegalStateException("Unable to find user information from security Context.");
  }
}
