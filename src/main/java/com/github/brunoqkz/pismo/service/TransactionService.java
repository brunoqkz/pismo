package com.github.brunoqkz.pismo.service;

import com.github.brunoqkz.pismo.dto.TransactionDTO;
import com.github.brunoqkz.pismo.model.OperationType;
import com.github.brunoqkz.pismo.model.Transaction;
import com.github.brunoqkz.pismo.repository.OperationTypeRepository;
import com.github.brunoqkz.pismo.repository.TransactionRepository;
import com.github.brunoqkz.pismo.service.exception.OperationTypeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

  private final TransactionRepository transactionRepository;
  private final OperationTypeRepository operationTypeRepository;
  private final ModelMapper modelMapper;

  public Transaction save(TransactionDTO transactionDTO) {
    final Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);

    transaction.setAmount(
        calculateAmount(transaction.getAmount(), transaction.getOperationType().getId()));

    return transactionRepository.save(transaction);
  }

  private Double calculateAmount(final Double amount, final Long operationTypeId) {
    final OperationType operationType = operationTypeRepository.findById(operationTypeId)
        .orElseThrow(() -> new OperationTypeNotFoundException(operationTypeId));
    final String operationTypeDescription = operationType
        .getDescription().toUpperCase();

    Double calculatedAmount;
    if (operationTypeDescription.startsWith("COMPRA") ||
        operationTypeDescription.startsWith("SAQUE")) {
      calculatedAmount = amount * -1;
    } else {
      calculatedAmount = amount;
    }
    return calculatedAmount;
  }
}
