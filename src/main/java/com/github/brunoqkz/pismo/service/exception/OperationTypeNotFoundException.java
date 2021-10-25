package com.github.brunoqkz.pismo.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OperationTypeNotFoundException extends RuntimeException {

  public OperationTypeNotFoundException(Long operationTypeId) {
    super(String.format("No operation type found with id: %s", operationTypeId));
  }

}
