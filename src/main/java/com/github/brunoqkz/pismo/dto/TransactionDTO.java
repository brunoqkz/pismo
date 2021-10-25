package com.github.brunoqkz.pismo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.brunoqkz.pismo.validation.AccountExists;
import com.github.brunoqkz.pismo.validation.OperationTypeExists;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

  @JsonProperty("account_id")
  @NotNull
  @Positive
  @AccountExists
  private Long accountId;

  @JsonProperty("operation_type_id")
  @NotNull
  @Positive
  @OperationTypeExists
  private Long operationTypeId;

  @NotNull
  @Positive
  private Double amount;

}
