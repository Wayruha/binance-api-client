package com.binance.api.client.domain.general;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinInfo {
    private String coin;
    private boolean depositAllEnable;
    private BigDecimal free;
    private BigDecimal freeze;
    private BigDecimal ipoable;
    private BigDecimal ipoing;
    @JsonProperty("isLegalMoney")
    private boolean isLegalMoney;
    private BigDecimal locked;
    private String name;
    private List<Network> networkList;
    private BigDecimal storage;
    private boolean trading;
    private boolean withdrawAllEnable;
    private BigDecimal withdrawing;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Network {
        private String addressRegex;
        private String coin;
        private String depositDesc;
        private boolean depositEnable;
        @JsonProperty("isDefault")
        private boolean isDefault;
        private String memoRegex;
        private int minConfirm;
        private String name;
        private String network;
        private boolean resetAddressStatus;
        private String specialTips;
        private int unLockConfirm;
        private String withdrawDesc;
        private boolean withdrawEnable;
        private BigDecimal withdrawFee;
        private BigDecimal withdrawIntegerMultiple;
        private BigDecimal withdrawMax;
        private BigDecimal withdrawMin;
        private boolean sameAddress;
        private BigDecimal depositDust;
        private String specialWithdrawTips;
        private String addressRule;
    }
}
