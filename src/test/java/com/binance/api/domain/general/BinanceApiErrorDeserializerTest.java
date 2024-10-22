package com.binance.api.domain.general;

import com.binance.api.client.BinanceApiError;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BinanceApiErrorDeserializerTest {
    final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testCodeMsgDeserialization() throws IOException {
        final String json = "{\"code\":-4051,\"msg\":\"someError message\"}";
        final BinanceApiError error = mapper.readValue(json, BinanceApiError.class);

        assertEquals(-4051, (long) error.getCode());
        assertEquals("someError message", error.getMsg());
        assertNull(error.getTimestamp());
        assertNull(error.getPath());
    }

    @Test
    public void testTsAndPathDeserialization() throws IOException {
        final String json = "{\"timestamp\":1638827401078,\"path\":\"/fapi/v1/order\",\"msg\":\"Unknown error, please check your request or try again later.\"}";
        final BinanceApiError error = mapper.readValue(json, BinanceApiError.class);

        assertEquals(1638827401078L, (long) error.getTimestamp());
        assertEquals("/fapi/v1/order", error.getPath());
        assertEquals("Unknown error, please check your request or try again later.", error.getMsg());
        assertNull(error.getCode());
    }

    @Test
    public void testUnknownFieldsDeserialization() throws IOException {
        final String json = "{\"code\":-4051,\"msg\":\"someError message\", \"nonExistingProperty\":-99}";
        final BinanceApiError error = mapper.readValue(json, BinanceApiError.class);

        assertEquals(-4051, (long) error.getCode());
        assertEquals("someError message", error.getMsg());
        assertNull(error.getTimestamp());
        assertNull(error.getPath());
    }
}
