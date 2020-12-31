package com.rout.usermgmt.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author sagar@sagarrout.com
 */
public class UserDtoAlias {

  private UUID id;

  @NotNull private String firstName;

  @NotNull private String lastName;

  @NotNull private LocalDateTime dob;

  @NotNull private String emailAddress;

  @NotNull private String phoneNumber;

  public UUID getId() {
    return id;
  }

  public UserDtoAlias setId(UUID id) {
    this.id = id;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public UserDtoAlias setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public UserDtoAlias setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public LocalDateTime getDob() {
    return dob;
  }

  public UserDtoAlias setDob(LocalDateTime dob) {
    this.dob = dob;
    return this;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public UserDtoAlias setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
    return this;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public UserDtoAlias setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }
}
