package com.github.brunoqkz.pismo.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.brunoqkz.pismo.BaseTest;
import com.github.brunoqkz.pismo.dto.AccountInputDTO;
import com.github.brunoqkz.pismo.dto.TransactionDTO;
import com.github.brunoqkz.pismo.model.Account;
import com.github.brunoqkz.pismo.repository.AccountRepository;
import com.github.brunoqkz.pismo.service.AccountService;
import java.util.Objects;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc()
@Transactional
class TransactionControllerTest extends BaseTest {

  static String TRANSACTION_ENDPOINT = "/transactions";

  @Autowired
  MockMvc mockMvc;

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
  @DisplayName("Should create a valid Transaction")
  @SneakyThrows
  @Rollback
  void shouldCreateTransaction() {
    TransactionDTO transactionDTO = TransactionDTO.builder()
        .amount(100.0)
        .operationTypeId(4L)
        .accountId(account.getId())
        .build();

    mockMvc.perform(post(TRANSACTION_ENDPOINT)
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(transactionDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.amount").value(transactionDTO.getAmount()))
        .andExpect(jsonPath("$.operationType.id").value(transactionDTO.getOperationTypeId()))
        .andExpect(jsonPath("$.account.id").value(transactionDTO.getAccountId()))
        .andReturn();
  }

  @Test
  @DisplayName("Should fail to persist transaction with nonexistent Operation Type")
  @SneakyThrows
  void shouldFailToCreateTransactionWithNonexistentOperationType() {
    TransactionDTO transactionDTO = TransactionDTO.builder()
        .amount(100.0)
        .operationTypeId(9999L)
        .accountId(account.getId())
        .build();

    MvcResult result = mockMvc.perform(post(TRANSACTION_ENDPOINT)
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(transactionDTO)))
        .andExpect(status().isBadRequest())
        .andReturn();

    assertThat(Objects.requireNonNull(result.getResolvedException()).getMessage(),
        containsString("Operation Type does not exists"));

  }

  @Test
  @DisplayName("Should fail to persist transaction with nonexistent Account")
  @SneakyThrows
  void shouldFailToCreateTransactionWithNonexistentAccount() {
    TransactionDTO transactionDTO = TransactionDTO.builder()
        .amount(100.0)
        .operationTypeId(1L)
        .accountId(10000L)
        .build();

    MvcResult result = mockMvc.perform(post(TRANSACTION_ENDPOINT)
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(transactionDTO)))
        .andExpect(status().isBadRequest())
        .andReturn();

    assertThat(Objects.requireNonNull(result.getResolvedException()).getMessage(),
        containsString("Account does not exists"));

  }

  @Test
  @DisplayName("Should fail to persist transaction with negative amount value")
  @SneakyThrows
  void shouldFailToCreateTransactionWithNegativeAmountValue() {
    TransactionDTO transactionDTO = TransactionDTO.builder()
        .amount(-100.0)
        .operationTypeId(1L)
        .accountId(account.getId())
        .build();

    mockMvc.perform(post(TRANSACTION_ENDPOINT)
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(transactionDTO)))
        .andExpect(status().isBadRequest())
        .andReturn();
  }

  @Test
  @DisplayName("Should fail to persist transaction with all fields incorrect")
  @Rollback
  void shouldFailToCreateInvalidTransaction() throws Exception {
    TransactionDTO transactionDTO = TransactionDTO.builder()
        .amount(-100.0)
        .operationTypeId(9999L)
        .accountId(10000L)
        .build();

    MvcResult result = mockMvc.perform(post(TRANSACTION_ENDPOINT)
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(transactionDTO)))
        .andExpect(status().isBadRequest())
        .andReturn();

    String exceptionMessage = Objects.requireNonNull(result.getResolvedException()).getMessage();
    assertThat(exceptionMessage, containsString("Account does not exists"));
    assertThat(exceptionMessage, containsString("Operation Type does not exists"));
  }

}