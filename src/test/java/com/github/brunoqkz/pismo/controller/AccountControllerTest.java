package com.github.brunoqkz.pismo.controller;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.brunoqkz.pismo.BaseTest;
import com.github.brunoqkz.pismo.dto.AccountInputDTO;
import com.jayway.jsonpath.JsonPath;
import lombok.SneakyThrows;
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
class AccountControllerTest extends BaseTest {

  static String ACCOUNT_ENDPOINT = "/accounts";

  @Autowired
  MockMvc mockMvc;

  @Test
  @DisplayName("Should persist account and retrieve account")
  @SneakyThrows
  @Rollback
  void shouldPersistAccount() {
    AccountInputDTO accountInputDTO = AccountInputDTO.builder().document("1234").build();

    MvcResult createAccountResult = mockMvc.perform(post(ACCOUNT_ENDPOINT)
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(accountInputDTO)))
        .andExpect(status().isCreated())
        .andReturn();

    int accountId = JsonPath.read(createAccountResult.getResponse().getContentAsString(),
        "$.id");

    mockMvc.perform(get(String.format("%s/%s", ACCOUNT_ENDPOINT, accountId)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.document_number").value("1234"));
  }

  @Test
  @DisplayName("Should fail to persist account with empty document number")
  @SneakyThrows
  void shouldFailToPersistAccount() {
    AccountInputDTO accountInputDTO = AccountInputDTO.builder().document(null).build();

    mockMvc.perform(post(ACCOUNT_ENDPOINT)
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(accountInputDTO)))
        .andExpect(status().isBadRequest())
        .andReturn();
  }

}