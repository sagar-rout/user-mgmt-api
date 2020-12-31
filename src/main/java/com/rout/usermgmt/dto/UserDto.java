package com.rout.usermgmt.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

/** @author sagar@sagarrout.com */
public class UserDto {

  private static final int MINIMUM_AGE = 18;

  @NotBlank(message = "firstName is required.")
  private String firstName;

  private String lastName;

  @NotNull(message = "password is required.")
  @Size(max = 20, min = 8, message = "password length should be in between 8 and 20.")
  private String password;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
  @NotNull(message = "dob is required.")
  @Past(message = "dob cannot be present or future")
  private LocalDateTime dob;

  @NotBlank(message = "email is required.")
  @Email(message = "emailAddress is not valid.")
  private String emailAddress;

  @NotBlank(message = "phoneNumber is required.")
  private String phoneNumber;

  @AssertTrue(message = "age is less than 18.")
  @JsonIgnore
  public boolean isDobLessThan18() {
    if (dob != null) {
      return Period.between(LocalDate.now(), dob.toLocalDate()).getYears() < MINIMUM_AGE;
    }
    return false;
  }

  public String getFirstName() {
    return firstName;
  }

  public UserDto setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public UserDto setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserDto setPassword(String password) {
    this.password = password;
    return this;
  }

  public LocalDateTime getDob() {
    return dob;
  }

  public UserDto setDob(LocalDateTime dob) {
    this.dob = dob;
    return this;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public UserDto setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
    return this;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public UserDto setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("UserDto{");
    sb.append("firstName='").append(firstName).append('\'');
    sb.append(", lastName='").append(lastName).append('\'');
    sb.append(", dob=").append(dob);
    sb.append(", emailAddress='").append(emailAddress).append('\'');
    sb.append(", phoneNumber='").append(phoneNumber).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
