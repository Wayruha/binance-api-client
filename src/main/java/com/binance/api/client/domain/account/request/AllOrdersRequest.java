package com.binance.api.client.domain.account.request;

import com.binance.api.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * A specialized order request with additional filters.
 */
public class AllOrdersRequest extends OrderRequest {

  private static final Integer DEFAULT_LIMIT = 500;

  private Long orderId;

  private Integer limit;

  private Long startTime;

  private Long endTime;

  public AllOrdersRequest(String symbol) {
    super(symbol);
    this.limit = DEFAULT_LIMIT;
  }

  public Long getOrderId() {
    return orderId;
  }

  public AllOrdersRequest orderId(Long orderId) {
    this.orderId = orderId;
    return this;
  }

  public Integer getLimit() {
    return limit;
  }

  public AllOrdersRequest limit(Integer limit) {
    this.limit = limit;
    return this;
  }

  public Long getStartTime() {
    return startTime;
  }

  public AllOrdersRequest startTime(Long startTime) {
    this.startTime = startTime;
    return this;
  }

  public Long getEndTime() {
    return endTime;
  }

  public AllOrdersRequest endTime(Long endTime) {
    this.endTime = endTime;
    return this;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
        .append("orderId", orderId)
        .append("limit", limit)
        .toString();
  }
}
