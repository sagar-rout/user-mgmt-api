package com.rout.usermgmt.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;

/** @author sagarrout.com */
public class PasswordRequestDto {

  @NotBlank(message = "current password is required.")
  private String currentPasswordInPlainText;

  @NotBlank(message = "new password is required.")
  private String newPasswordInPlainText;

  @NotBlank(message = "confirm new password is required.")
  private String confirmNewPasswordInPlainText;

  private PasswordRequestDto() {
    // jackson needs this for serialization
  }

  public PasswordRequestDto(
      String currentPasswordInPlainText,
      String newPasswordInPlainText,
      String confirmNewPasswordInPlainText) {
    this.currentPasswordInPlainText = currentPasswordInPlainText;
    this.newPasswordInPlainText = newPasswordInPlainText;
    this.confirmNewPasswordInPlainText = confirmNewPasswordInPlainText;
  }

  public String getCurrentPasswordInPlainText() {
    return currentPasswordInPlainText;
  }

  public PasswordRequestDto setCurrentPasswordInPlainText(String currentPasswordInPlainText) {
    this.currentPasswordInPlainText = currentPasswordInPlainText;
    return this;
  }

  public String getNewPasswordInPlainText() {
    return newPasswordInPlainText;
  }

  public PasswordRequestDto setNewPasswordInPlainText(String newPasswordInPlainText) {
    this.newPasswordInPlainText = newPasswordInPlainText;
    return this;
  }

  public String getConfirmNewPasswordInPlainText() {
    return confirmNewPasswordInPlainText;
  }

  public PasswordRequestDto setConfirmNewPasswordInPlainText(String confirmNewPasswordInPlainText) {
    this.confirmNewPasswordInPlainText = confirmNewPasswordInPlainText;
    return this;
  }

  @AssertTrue(message = "New Password should not match with current password.")
  @JsonIgnore
  public boolean isCurrentAndNewPasswordSame() {
    return currentPasswordInPlainText.equals(newPasswordInPlainText);
  }

  @AssertFalse(message = "New Password and confirm password are not matching.")
  @JsonIgnore
  public boolean areNewAndConfirmPassword() {
    return newPasswordInPlainText.equals(confirmNewPasswordInPlainText);
  }
}
