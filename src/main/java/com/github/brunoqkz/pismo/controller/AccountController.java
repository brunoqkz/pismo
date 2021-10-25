package com.github.brunoqkz.pismo.controller;

import com.github.brunoqkz.pismo.dto.AccountInputDTO;
import com.github.brunoqkz.pismo.dto.AccountOutputDTO;
import com.github.brunoqkz.pismo.model.Account;
import com.github.brunoqkz.pismo.service.AccountService;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@Validated
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public Account createAccount(@RequestBody @Valid AccountInputDTO accountInputDTO) {
    return accountService.save(accountInputDTO);
  }

  @GetMapping("/{accountId}")
  public AccountOutputDTO getAccountById(@PathVariable @Positive Long accountId) {
    return accountService.findById(accountId);
  }

}
