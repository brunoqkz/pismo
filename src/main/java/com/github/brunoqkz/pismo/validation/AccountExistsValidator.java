package com.github.brunoqkz.pismo.validation;

import static java.util.Objects.isNull;

import com.github.brunoqkz.pismo.repository.AccountRepository;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountExistsValidator implements ConstraintValidator<AccountExists, Long> {

  @Autowired
  private AccountRepository accountRepository;

  @Override
  public void initialize(AccountExists constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(Long accountId, ConstraintValidatorContext context) {
    if (isNull(accountId)) {
      return false;
    }
    return accountRepository.existsById(accountId);
  }
}
