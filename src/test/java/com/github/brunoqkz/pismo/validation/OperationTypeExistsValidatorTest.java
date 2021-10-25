package com.github.brunoqkz.pismo.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.github.brunoqkz.pismo.repository.OperationTypeRepository;
import javax.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class OperationTypeExistsValidatorTest {

  @Mock
  OperationTypeRepository operationTypeRepository;

  @Mock
  ConstraintValidatorContext context;

  @InjectMocks
  OperationTypeExistsValidator operationTypeExistsValidator;

  @Test
  @DisplayName("Should return true when operation type already exists")
  void shouldReturnTrueWhenOperationTypeExists() {
    Long existingOperationTypeId = 1L;
    when(operationTypeRepository.existsById(any())).thenReturn(true);
    assertTrue(operationTypeExistsValidator.isValid(existingOperationTypeId, context));
  }

  @Test
  @DisplayName("Should return false when operation type doesn't exists")
  void shouldReturnFalseWhenOperationTypeDoesNotExists() {
    Long nonExistingOperationTypeId = 1L;
    when(operationTypeRepository.existsById(any())).thenReturn(false);
    assertFalse(operationTypeExistsValidator.isValid(nonExistingOperationTypeId, context));
  }

  @Test
  @DisplayName("Should return false when operation type id is null")
  void shouldReturnFalseWhenOperationTypeIdIsNull() {
    assertFalse(operationTypeExistsValidator.isValid(null, context));
  }
}