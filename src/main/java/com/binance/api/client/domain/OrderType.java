package com.binance.api.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Type of order to submit to the system.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum OrderType {
  LIMIT,
  MARKET,
  STOP_LOSS,
  STOP_LOSS_LIMIT,
  STOP_LOSS_MARKET,
  TAKE_PROFIT,
  TAKE_PROFIT_LIMIT,
  TAKE_PROFIT_MARKET,
  LIMIT_MAKER,

  //Futures-specific
  TRAILING_STOP_MARKET,
  STOP,
  LIQUIDATION,
  STOP_MARKET;
}
