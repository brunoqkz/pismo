package com.github.brunoqkz.pismo.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.brunoqkz.pismo.model.Transaction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class TransactionDTOConversionTest {

  ModelMapper modelMapper = new ModelMapper();

  @Test
  @DisplayName("Should convert DTO into Transaction Object")
  void shouldConvertTransactionObjectToDTO() {
    TransactionDTO transactionDTO = new TransactionDTO();
    transactionDTO.setAmount(100.0);
    transactionDTO.setAccountId(1L);
    transactionDTO.setOperationTypeId(1L);

    Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);

    assertEquals(transactionDTO.getAmount(), transaction.getAmount());
    assertEquals(transactionDTO.getAccountId(), transaction.getAccount().getId());
    assertEquals(transactionDTO.getOperationTypeId(), transaction.getOperationType().getId());
  }

}