package com.binance.api.client.domain.account;

import com.binance.api.client.constant.BinanceApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * MarginTransaction information.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Transaction {

  private String tranId;
  private String clientTag;

  @Override
  public String toString() {
    return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
        .append("transactionId", tranId)
        .append("clientTag", clientTag)
        .toString();
  }
}
