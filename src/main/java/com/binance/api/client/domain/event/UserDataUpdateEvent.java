package com.binance.api.client.domain.event;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.event.mapper.UserDataUpdateEventDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.apache.commons.lang3.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User data update event which can be of four types:
 * <p>
 * 1) outboundAccountInfo, whenever there is a change in the account (e.g. balance of an asset)
 * 2) outboundAccountPosition, the change in account balances caused by an event.
 * 3) executionReport, whenever there is a trade or an order
 * 4) balanceUpdate, the change in account balance (delta).
 * <p>
 * Deserialization could fail with UnsupportedEventException in case of unsupported eventType.
 */
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"notParsableData"})
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = UserDataUpdateEventDeserializer.class)
public class UserDataUpdateEvent extends AbstractEvent {

    private AccountUpdateEvent accountUpdateEvent;

    private BalanceUpdateEvent balanceUpdateEvent;

    private OrderTradeUpdateEvent orderTradeUpdateEvent;

    private FuturesTradeEvent futuresTradeEvent;

    private FuturesAccountUpdateEvent futuresAccountUpdateEvent;

    private FuturesAccConfigEvent futuresAccConfigEvent;

    private MarginCallEvent marginCallEvent;

    private CoinSwapEvent coinSwapEvent;

    private OCOTradeEvent ocoTradeEvent;

    private String notParsableData;

    @Override
    public String toString() {
        ToStringBuilder sb = new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("content", ObjectUtils.firstNonNull(accountUpdateEvent, balanceUpdateEvent, orderTradeUpdateEvent, futuresTradeEvent, futuresAccountUpdateEvent, futuresAccConfigEvent, marginCallEvent, coinSwapEvent, ocoTradeEvent))
                .append("eventType", eventType);
        if (StringUtils.isNotEmpty(notParsableData))
            sb.append("errorData", notParsableData);
        return sb.toString();
    }

    public AbstractEvent contentEvent() {
        switch (getEventType()) {
            case ACCOUNT_POSITION_UPDATE:
                return getAccountUpdateEvent();
            case BALANCE_UPDATE:
                return getBalanceUpdateEvent();
            case ORDER_TRADE_UPDATE:
                return getOrderTradeUpdateEvent();
            case FUTURES_ORDER_TRADE_UPDATE:
                return getFuturesTradeEvent();
            case FUTURES_ACCOUNT_UPDATE:
                return getFuturesAccountUpdateEvent();
            case ACCOUNT_CONFIG_UPDATE:
                return getFuturesAccConfigEvent();
            case MARGIN_CALL:
                return getMarginCallEvent();
            case COIN_SWAP_ORDER:
                return getCoinSwapEvent();
        }
        return null;
    }
}
