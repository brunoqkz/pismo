package com.github.brunoqkz.pismo.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.github.brunoqkz.pismo.BaseTest;
import com.github.brunoqkz.pismo.dto.AccountInputDTO;
import com.github.brunoqkz.pismo.dto.TransactionDTO;
import com.github.brunoqkz.pismo.model.Account;
import com.github.brunoqkz.pismo.model.Transaction;
import com.github.brunoqkz.pismo.repository.AccountRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class TransactionServiceTest extends BaseTest {

  @Autowired
  TransactionService transactionService;

  static Account account;

  @BeforeAll
  public static void setupAccount(@Autowired AccountService accountService) {
    account = accountService.save(AccountInputDTO.builder().document("1234").build());
  }

  @AfterAll
  public static void cleanUp(@Autowired AccountRepository accountRepository) {
    accountRepository.deleteAll();
  }

  @Test
  @DisplayName("Should successfully persist and retrieve transaction")
  @Rollback
  void shouldPersistAndRetrieveValidTransaction() {
    TransactionDTO transactionDTO = new TransactionDTO();
    transactionDTO.setAccountId(account.getId());
    transactionDTO.setOperationTypeId(1L);
    transactionDTO.setAmount(100.0);

    Transaction transaction = transactionService.save(transactionDTO);

    assertThat(transactionDTO.getAccountId(), equalTo(transaction.getAccount().getId()));
    assertThat(transactionDTO.getOperationTypeId(),
        equalTo(transaction.getOperationType().getId()));
    assertThat(transactionDTO.getAccountId(), equalTo(transaction.getAccount().getId()));
    assertThat(transactionDTO.getAmount(), equalTo(transaction.getAmount() * -1));
  }

}