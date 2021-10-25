package com.github.brunoqkz.pismo.controller;

import com.github.brunoqkz.pismo.dto.TransactionDTO;
import com.github.brunoqkz.pismo.model.Transaction;
import com.github.brunoqkz.pismo.service.TransactionService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

  private final TransactionService transactionService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Transaction createTransaction(@RequestBody @Valid TransactionDTO transactionDTO) {
    return transactionService.save(transactionDTO);
  }

}
