package com.github.brunoqkz.pismo.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends RuntimeException {

  public AccountNotFoundException(Long accountId) {
    super(String.format("No account found with id: %s", accountId));
  }
}
