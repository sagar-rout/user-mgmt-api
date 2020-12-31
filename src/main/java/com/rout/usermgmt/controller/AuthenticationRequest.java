package com.rout.usermgmt.controller;

import javax.validation.constraints.NotBlank;

/** @author sagar@sagarrout.com */
public class AuthenticationRequest {

  @NotBlank(message = "emailAddress is required.")
  private String emailAddress;

  @NotBlank(message = "password is required.")
  private String password;

  private AuthenticationRequest() {
    // jackson needs this default constructor
  }

  public AuthenticationRequest(String emailAddress, String password) {
    this.emailAddress = emailAddress;
    this.password = password;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public AuthenticationRequest setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public AuthenticationRequest setPassword(String password) {
    this.password = password;
    return this;
  }
}
