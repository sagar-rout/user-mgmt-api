package com.rout.usermgmt.controller;

import com.rout.usermgmt.dto.UserDto;
import com.rout.usermgmt.dto.UserDtoAlias;
import com.rout.usermgmt.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/** @author sagar@sagarrout.com */
@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(value = "/users/{id}", produces = APPLICATION_JSON_VALUE)
  public UserDtoAlias getById(@PathVariable("id") UUID id) {
    return userService.getById(id);
  }

  @PostMapping(
      value = "/users",
      produces = APPLICATION_JSON_VALUE,
      consumes = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public UserDtoAlias create(@Valid @RequestBody UserDto userDto) {
    return userService.create(userDto);
  }

  @PutMapping(
      value = "/users/{id}",
      produces = APPLICATION_JSON_VALUE,
      consumes = APPLICATION_JSON_VALUE)
  public UserDtoAlias update(@RequestBody UserDtoAlias userDtoAlias, @PathVariable("id") UUID id) {
    if(!userDtoAlias.getId().equals(id)){
      throw new IllegalArgumentException("Path variable id and request id are not matching");
    }
    return userService.update(userDtoAlias, id);
  }

  @DeleteMapping(value = "/users/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable("id") UUID id) {
    userService.deleteById(id);
  }
}
