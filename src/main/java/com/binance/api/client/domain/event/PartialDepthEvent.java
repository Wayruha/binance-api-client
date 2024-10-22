package com.binance.api.client.domain.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PartialDepthEvent {
    private long lastUpdateId;
    private List<PriceLevel> bids;
    private List<PriceLevel> asks;

    @JsonDeserialize(using = PriceLevelDeserializer.class)
    @Data
    @AllArgsConstructor
    public static class PriceLevel {
        private BigDecimal priceLevel;
        private BigDecimal qty;
    }

    public static class PriceLevelDeserializer extends JsonDeserializer<PriceLevel> {
        @Override
        public PriceLevel deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            final BigDecimal[] order = parser.getCodec().readValue(parser, BigDecimal[].class);
            return new PriceLevel(order[0], order[1]);
        }
    }
}
