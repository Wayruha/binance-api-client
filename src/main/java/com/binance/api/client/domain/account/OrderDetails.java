package com.binance.api.client.domain.account;

import com.binance.api.client.domain.*;

import java.math.BigDecimal;

public interface OrderDetails {
    String getSymbol();

    OrderSide getSide();

    OrderType getType();

    TimeInForce getTimeInForce();

    String getOriginalQuantity();

//    BigDecimal getQuoteQty();

    String getPrice();

    String getNewClientOrderId();

    OrderStatus getOrderStatus();

    Long getOrderId();

    String getActualOrderId();
}
