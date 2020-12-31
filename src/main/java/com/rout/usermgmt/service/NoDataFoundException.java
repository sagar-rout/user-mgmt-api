package com.rout.usermgmt.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** @author sagar@sagarrout.com */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoDataFoundException extends RuntimeException {
  public NoDataFoundException() {
    super();
  }

  public NoDataFoundException(String message) {
    super(message);
  }
}
