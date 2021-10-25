package com.github.brunoqkz.pismo.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.github.brunoqkz.pismo.repository.AccountRepository;
import javax.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class AccountExistsValidatorTest {

  @Mock
  AccountRepository accountRepository;

  @Mock
  ConstraintValidatorContext context;

  @InjectMocks
  AccountExistsValidator accountExistsValidator;

  @Test
  @DisplayName("Should return true when account already exists")
  void shouldReturnTrueWhenAccountAlreadyExists() {
    Long existingAccountId = 1L;
    when(accountRepository.existsById(any())).thenReturn(true);
    assertTrue(accountExistsValidator.isValid(existingAccountId, context));
  }

  @Test
  @DisplayName("Should return false when account doesn't exists")
  void shouldReturnFalseWhenAccountDoesNotExists() {
    Long nonExistingAccountId = 1L;
    when(accountRepository.existsById(any())).thenReturn(false);
    assertFalse(accountExistsValidator.isValid(nonExistingAccountId, context));
  }

  @Test
  @DisplayName("Should return false when account id is null")
  void shouldReturnFalseWhenAccountIdIsNull() {
    assertFalse(accountExistsValidator.isValid(null, context));
  }

}