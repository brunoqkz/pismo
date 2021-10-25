package com.github.brunoqkz.pismo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccountOutputDTO {

  @JsonProperty("account_id")
  private Long id;
  @JsonProperty("document_number")
  private String document;

}
