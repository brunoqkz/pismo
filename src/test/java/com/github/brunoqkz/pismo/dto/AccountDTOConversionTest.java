package com.github.brunoqkz.pismo.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.brunoqkz.pismo.model.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class AccountDTOConversionTest {

  ModelMapper modelMapper = new ModelMapper();

  @Test
  @DisplayName("Should successfully convert Account object into DTO")
  void shouldConvertAccountToDTO() {
    Account account = new Account();
    account.setId(1L);
    account.setDocument("1");

    AccountOutputDTO accountOutputDTO = modelMapper.map(account, AccountOutputDTO.class);

    assertEquals(account.getId(), accountOutputDTO.getId());
    assertEquals(account.getDocument(), accountOutputDTO.getDocument());
  }

  @Test
  @DisplayName("Should successfully convert DTO into Account object")
  void shouldConvertDTOToAccount() {
    AccountInputDTO accountInputDTO = new AccountInputDTO();
    accountInputDTO.setDocument("1");

    Account account = modelMapper.map(accountInputDTO, Account.class);

    assertEquals(accountInputDTO.getDocument(), account.getDocument());
  }

}