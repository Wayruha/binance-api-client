package com.binance.api.client.domain.account.margin;

import com.binance.api.client.domain.account.Withdraw;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * History of account withdrawals.
 *
 * @see Withdraw
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanQueryResult {

  private int total;
  private List<Loan> rows;

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public List<Loan> getRows() {
    return rows;
  }

  public void setRows(List<Loan> rows) {
    this.rows = rows;
  }
}
