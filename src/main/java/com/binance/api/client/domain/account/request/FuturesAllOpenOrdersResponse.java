package com.binance.api.client.domain.account.request;

import com.binance.api.client.domain.*;
import com.binance.api.client.domain.account.OrderDetails;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Data
public class FuturesAllOpenOrdersResponse implements OrderDetails {
    private String symbol;
    private OrderSide side;
    @Getter(AccessLevel.NONE)
    private String origQty;
    private PositionSide positionSide;
    @Getter(AccessLevel.NONE)
    private OrderStatus status;
    private boolean reduceOnly;
    private OrderType type;
    private TimeInForce timeInForce;
    private OrderType origType;
    private String avgPrice;
    private String cumQuote;
    private String executedQty;
    @Getter(AccessLevel.NONE)
    private String clientOrderId;
    private Long orderId;
    private String price;
    private String stopPrice;
    private boolean closePosition;
    private Long time;
    private String activationPrice;
    private String priceRate;
    private Long updateTime;
    private WorkingType workingType;
    private boolean priceProtect;

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

    @Override
    public Long getOrderId() {
        return orderId;
    }

    @Override
    public String getActualOrderId() {
        return StringUtils.isNotEmpty(clientOrderId) ? clientOrderId : orderId.toString();
    }
}
