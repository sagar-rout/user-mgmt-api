package com.rout.usermgmt.controller;

import com.rout.usermgmt.dto.PasswordRequestDto;
import com.rout.usermgmt.service.PasswordService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class PasswordController {

  private final PasswordService passwordService;

  public PasswordController(PasswordService passwordService) {
    this.passwordService = passwordService;
  }

  @PostMapping(
      value = "/passwords",
      produces = APPLICATION_JSON_VALUE,
      consumes = APPLICATION_JSON_VALUE)
  public void updatePassword(
      @Valid @RequestBody PasswordRequestDto passwordRequestDto) {
      passwordService.updatePassword(passwordRequestDto);
  }
}
