package com.github.brunoqkz.pismo.service;

import com.github.brunoqkz.pismo.dto.AccountInputDTO;
import com.github.brunoqkz.pismo.dto.AccountOutputDTO;
import com.github.brunoqkz.pismo.model.Account;
import com.github.brunoqkz.pismo.repository.AccountRepository;
import com.github.brunoqkz.pismo.service.exception.AccountNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

  private final ModelMapper modelMapper;
  private final AccountRepository accountRepository;

  @Transactional
  public AccountOutputDTO findById(Long accountId) {
    Account account = accountRepository.findById(accountId)
        .orElseThrow(() -> new AccountNotFoundException(accountId));
    return modelMapper.map(account, AccountOutputDTO.class);
  }

  @Transactional
  public Account save(AccountInputDTO accountInputDTO) {
    final Account account = modelMapper.map(accountInputDTO, Account.class);
    return accountRepository.save(account);
  }
}
