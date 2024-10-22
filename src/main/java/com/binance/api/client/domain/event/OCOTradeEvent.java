package com.binance.api.client.domain.event;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.*;
import com.binance.api.client.domain.account.Order;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
public class OCOTradeEvent extends AbstractEvent {
    @JsonProperty("s")
    private String symbol;

    @JsonProperty("g")
    private int orderListId;

    @JsonProperty("c")
    private ContingencyType contingencyType;

    @JsonProperty("l")
    private OCOStatus listStatusType;

    @JsonProperty("L")
    private OCOOrderStatus listOrderStatus;

    @JsonProperty("r")
    private String listRejectReason;

    @JsonProperty("C")
    private String listClientOrderId;

    @JsonProperty("O")
    private List<Order> orders;

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("symbol", symbol)
                .append("contingencyType", contingencyType)
                .append("listStatusType", listStatusType)
                .append("listOrderStatus", listOrderStatus)
                .append("listRejectReason", listRejectReason)
                .append("listClientOrderId", listClientOrderId)
                .append("orderListId", orderListId)
                .append("orders", orders)
                .toString();
    }
}
