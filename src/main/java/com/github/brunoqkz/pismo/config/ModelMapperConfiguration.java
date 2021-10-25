package com.github.brunoqkz.pismo.config;

import com.github.brunoqkz.pismo.dto.AccountInputDTO;
import com.github.brunoqkz.pismo.dto.TransactionDTO;
import com.github.brunoqkz.pismo.model.Account;
import com.github.brunoqkz.pismo.model.Transaction;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();

    TypeMap<TransactionDTO, Transaction> typeTransaction = modelMapper.createTypeMap(TransactionDTO.class, Transaction.class);
    typeTransaction.addMappings(mapper -> mapper.skip(Transaction::setId));

    TypeMap<AccountInputDTO, Account> typeAccount = modelMapper.createTypeMap(AccountInputDTO.class, Account.class);
    typeAccount.addMappings(mapper -> mapper.skip(Account::setId));

    return modelMapper;
  }

}
