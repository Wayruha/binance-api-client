package com.binance.api.client.domain.account.margin;

import com.binance.api.client.domain.account.Withdraw;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Max Borrow Query Result
 *
 * @see Withdraw
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MaxBorrowableQueryResult {

  private String amount;

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  @Override
  public String toString() {
    return "MaxBorrowQueryResult{" +
            "amount='" + amount + '\'' +
            '}';
  }
}
