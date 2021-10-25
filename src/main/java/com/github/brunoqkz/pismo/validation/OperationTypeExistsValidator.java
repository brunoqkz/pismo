package com.github.brunoqkz.pismo.validation;

import static java.util.Objects.isNull;

import com.github.brunoqkz.pismo.repository.OperationTypeRepository;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class OperationTypeExistsValidator implements ConstraintValidator<OperationTypeExists, Long> {

  @Autowired
  private OperationTypeRepository OperationTypeRepository;

  @Override
  public void initialize(OperationTypeExists constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(Long operationTypeId, ConstraintValidatorContext context) {
    if (isNull(operationTypeId)) {
      return false;
    }
    return OperationTypeRepository.existsById(operationTypeId);
  }
}
