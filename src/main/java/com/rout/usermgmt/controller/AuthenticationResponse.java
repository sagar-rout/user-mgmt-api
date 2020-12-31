package com.rout.usermgmt.controller;

/** @author sagar@sagarrout.com */
public class AuthenticationResponse {

  private String token;

  public AuthenticationResponse(String token) {
    this.token = token;
  }

  private AuthenticationResponse() {
    // jackson needs default constructor
  }

  public String getToken() {
    return token;
  }

  public AuthenticationResponse setToken(String token) {
    this.token = token;
    return this;
  }
}
