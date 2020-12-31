package com.rout.usermgmt.domain;

/**
 * @author sagar@sagarrout.com
 */
public enum RoleType {
  ROLE_USER("ROLE_USER"),
  ROLE_ADMIN("ROLE_ADMIN");

  private final String text;

  RoleType(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}
