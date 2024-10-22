package com.binance.api.client.domain.account;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Response returned when placing a new order on the system.
 *
 * @see NewOrder for the request
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewOrderResponse implements OrderDetails {

  /**
   * Order symbol.
   */
  private String symbol;

  /**
   * Order id.
   */
  private Long orderId;

  /**
   * This will be either a generated one, or the newClientOrderId parameter
   * which was passed when creating the new order.
   */
  @Getter(AccessLevel.NONE)
  private String clientOrderId;

  private String price;

  @Getter(AccessLevel.NONE)
  private String origQty;

  private String executedQty;

  private String cummulativeQuoteQty;

  @Getter(AccessLevel.NONE)
  private OrderStatus status;

  private TimeInForce timeInForce;

  private OrderType type;

  private OrderSide side;

  // @JsonSetter(nulls = Nulls.AS_EMPTY)
  private List<Trade> fills;

  /**
   * Transact time for this order.
   */
  private Long transactTime;


  @Override
  public String getOriginalQuantity() {
    return origQty;
  }

  @Override
  public String getNewClientOrderId() {
    return clientOrderId;
  }

  @Override
  public OrderStatus getOrderStatus() {
    return status;
  }

  public String getActualOrderId() {
    return StringUtils.isNotEmpty(clientOrderId) ? clientOrderId : orderId.toString();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
        .append("symbol", symbol)
        .append("orderId", orderId)
        .append("clientOrderId", clientOrderId)
        .append("transactTime", transactTime)
        .append("price", price)
        .append("origQty", origQty)
        .append("executedQty", executedQty)
        .append("status", status)
        .append("timeInForce", timeInForce)
        .append("type", type)
        .append("side", side)
        .append("fills", Optional.ofNullable(fills).orElse(Collections.emptyList())
            .stream()
            .map(Object::toString)
            .collect(Collectors.joining(", ")))
        .toString();
  }
}
