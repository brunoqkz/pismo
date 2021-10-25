package com.github.brunoqkz.pismo.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.brunoqkz.pismo.BaseTest;
import com.github.brunoqkz.pismo.dto.AccountInputDTO;
import com.github.brunoqkz.pismo.dto.AccountOutputDTO;
import com.github.brunoqkz.pismo.model.Account;
import com.github.brunoqkz.pismo.service.exception.AccountNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class AccountServiceTest extends BaseTest {

  @Autowired
  AccountService accountService;

  @Test
  @DisplayName("Should successfully persist and retrieve account")
  @Rollback
  void shouldPersistAndRetrieveValidAccount() {
    AccountInputDTO accountInputDTO = new AccountInputDTO();
    accountInputDTO.setDocument("1234");
    Account account = accountService.save(accountInputDTO);
    AccountOutputDTO accountOutputDTO = accountService.findById(account.getId());
    assertThat(accountOutputDTO.getDocument(), equalTo(accountInputDTO.getDocument()));
  }

  @Test
  @DisplayName("Should throw exception when search for Account with nonexistent ID")
  void shouldThrowExceptionForNonexistentID() {
    AccountNotFoundException thrownException = assertThrows(AccountNotFoundException.class,
        () -> accountService.findById(1L));
    assertThat(thrownException.getMessage(), containsString("No account found with id: 1"));
  }

  @Test
  @DisplayName("Should throw exception when account without document number")
  void shouldThrowExceptionWhenPersistingAccountWithoutDocumentNumber() {
    DataIntegrityViolationException thrownException = assertThrows(
        DataIntegrityViolationException.class,
        () -> accountService.save(new AccountInputDTO()));
    assertThat(thrownException.getMessage(), containsString("document_number"));
  }

}