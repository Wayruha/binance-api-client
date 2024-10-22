package com.binance.api.client.exception;

import com.binance.api.client.BinanceApiError;
import retrofit2.Call;

public class InvalidAPIKeyException extends BinanceApiException {

    public InvalidAPIKeyException(BinanceApiError error) {
        super(error);
    }

    public InvalidAPIKeyException(BinanceApiError error, Call request, String errorResponse) {
        super(formMessage(error));
        this.error = error;
        this.request = request;
        this.errorResponse = errorResponse;
    }

    private static String formMessage(BinanceApiError error) {
        return String.format("Invalid API Keys: %s", error.toString());
    }
}
