package com.rout.usermgmt.controller;

import com.rout.usermgmt.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/** @author sagar@sagarrout.com */
@RestController
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  public AuthenticationController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @PostMapping(
      value = "/authenticate",
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public AuthenticationResponse authenticate(
      @Valid @RequestBody AuthenticationRequest authenticationRequest) {
    return authenticationService.getToken(authenticationRequest);
  }
}
