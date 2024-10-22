package com.binance.api.client.domain.general;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.OrderType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.util.Optional;

/**
 * Symbol information (base/quote).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class SymbolInfo {

    private String symbol;

    private String pair;

    private SymbolStatus status;

    private String baseAsset;

    private Integer baseAssetPrecision;

    private String quoteAsset;

    private Integer quotePrecision;

    private Integer quantityPrecision;

    private Integer pricePrecision;

    private List<OrderType> orderTypes;

    private boolean icebergAllowed;

    private List<SymbolFilter> filters;


    /**
     * @param filterType filter type to filter for.
     * @return symbol filter information for the provided filter type.
     */
    public Optional<SymbolFilter> getSymbolFilter(FilterType filterType) {
        return filters.stream()
                .filter(symbolFilter -> symbolFilter.getFilterType() == filterType)
                .findFirst();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("symbol", symbol)
                .append("status", status)
                .append("baseAsset", baseAsset)
                .append("baseAssetPrecision", baseAssetPrecision)
                .append("quoteAsset", quoteAsset)
                .append("quotePrecision", quotePrecision)
                .append("orderTypes", orderTypes)
                .append("icebergAllowed", icebergAllowed)
                .append("filters", filters)
                .append("pricePrecision", pricePrecision)
                .append("quantityPrecision", quantityPrecision)
                .toString();
    }
}
