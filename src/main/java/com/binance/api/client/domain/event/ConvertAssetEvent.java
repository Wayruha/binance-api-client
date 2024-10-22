package com.binance.api.client.domain.event;

import com.binance.api.client.domain.*;
import lombok.*;

import java.math.*;
import java.util.*;

/**
 * 'Convert fund' action spawns 4 events = 2 transactions.
 * Therefore, to build it we require all these events, in chronological order.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ConvertAssetEvent extends TradeEvent implements IdempotentUpdate {
    private final SimpleTransactionEvent deductEvent, addEvent;
    private final List<AbstractEvent> innerEvents;
    private BigDecimal baseQty;
    private BigDecimal quoteQty;
    private String baseAsset;
    private String quoteAsset;
    private boolean processed;

    public ConvertAssetEvent(SimpleTransactionEvent deductEvent, SimpleTransactionEvent addEvent) {
        super(addEvent);
        this.eventType = EventType.CONVERT_FUNDS;
        this.deductEvent = deductEvent;
        this.addEvent = addEvent;
        this.innerEvents = new ArrayList<>(Arrays.asList(deductEvent, addEvent));
        this.baseAsset = addEvent.getAsset();
        this.baseQty = addEvent.getDelta();
        this.quoteAsset = deductEvent.getAsset();
        assert deductEvent.getDelta().signum() <= 0 : "ConvertFunds Unexpected QuoteDelta > 0. " +
                                                              "DeductEvent:" + deductEvent + ", AddEvent: " + addEvent;
        this.quoteQty = deductEvent.getDelta().abs();

        setOrderTradeTime(addEvent.getEventTime());
        setOriginalQuantity(addEvent.getDelta().toPlainString());
        setSymbol(baseAsset + quoteAsset);
        setSide(OrderSide.BUY);
        initBase();
    }

    private ConvertAssetEvent() {
        this.deductEvent = null;
        this.addEvent = null;
        this.innerEvents = Collections.emptyList();
        this.eventType = EventType.CONVERT_FUNDS;
        this.eventTime = 0L;
        this.transaction = 0L;
    }

    public static ConvertAssetEvent syntheticInstance(String baseAsset, String quoteAsset, BigDecimal baseQty, BigDecimal quoteQty) {
        ConvertAssetEvent event = new ConvertAssetEvent();
        event.baseAsset = baseAsset;
        event.quoteAsset = quoteAsset;
        event.baseQty = baseQty;
        event.quoteQty = quoteQty;

        event.setOrderTradeTime(0L);
        event.setOriginalQuantity(baseQty.toPlainString());
        event.setSymbol(baseAsset + quoteAsset);
        event.setSide(OrderSide.BUY);
        event.initBase();

        return event;
    }

    private void initBase() {
        //requires some parameters to be already set.
        BigDecimal price = this.quoteQty.divide(baseQty, MathContext.DECIMAL32);
        setPrice(price.toPlainString());
        setQuantityLastFilledTrade(getOriginalQuantity());
        setPriceOfLastFilledTrade(getPrice());

        setType(OrderType.MARKET);
        setTimeInForce(TimeInForce.GTC);
        setExecutionType(ExecutionType.TRADE);
        setOrderStatus(OrderStatus.FILLED);
    }

    /**
     * Swap base and quote assets
     */
    public void reverseTradePair() {
        final BigDecimal tempDecimal = baseQty;
        final String temp = baseAsset;

        setSide(getSide().oppositeSide());
        this.baseAsset = this.quoteAsset;
        this.quoteAsset = temp;
        this.baseQty = this.quoteQty;
        this.quoteQty = tempDecimal;
        setSymbol(this.baseAsset + this.quoteAsset);
        setOriginalQuantity(this.baseQty.toPlainString());
        initBase();
    }

    @Override
    public String getOriginalQuantity() {
        return baseQty != null ? baseQty.toPlainString() : "0";
    }

    @Override
    public BigDecimal getQuoteQty() {
        return quoteQty;
    }

    @Override
    public String getActualOrderId() {
        return "id_" + deductEvent.getEventTime().toString();
    }
}
