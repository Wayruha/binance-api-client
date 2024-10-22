package com.binance.api.client.exception;

import com.binance.api.client.BinanceApiError;
import retrofit2.Call;

import static java.util.Optional.ofNullable;

/**
 * An exception which can occur while invoking methods of the Binance API.
 */
public class BinanceApiException extends RuntimeException {

    private static final long serialVersionUID = 3788669840036201041L;
    /**
     * Error response object returned by Binance API.
     */
    protected BinanceApiError error;

    protected Call request;
    protected String errorResponse;

    /**
     * Instantiates a new binance api exception.
     *
     * @param error an error response object
     */
    public BinanceApiException(BinanceApiError error) {
        super(formMessage(error, null, null));
        this.error = error;
    }

    public BinanceApiException(BinanceApiError error, Call request, String errorResponse) {
        super(formMessage(error, request, errorResponse));
        this.error = error;
        this.request = request;
        this.errorResponse = errorResponse;
    }

    /**
     * Instantiates a new binance api exception.
     */
    public BinanceApiException() {
        super();
    }

    /**
     * Instantiates a new binance api exception.
     *
     * @param message the message
     */
    public BinanceApiException(String message) {
        super(message);
    }

    /**
     * Instantiates a new binance api exception.
     *
     * @param cause the cause
     */
    public BinanceApiException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new binance api exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public BinanceApiException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @return the response error object from Binance API, or null if no response object was returned (e.g. server returned 500).
     */
    public BinanceApiError getError() {
        return error;
    }

    public String getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(String errorResponse) {
        this.errorResponse = errorResponse;
    }

    private static String formMessage(BinanceApiError error, Call request, String errorResponse) {
        if (error == null && errorResponse == null) return "";
        StringBuilder bldr = new StringBuilder();
        if (error != null) {
            final int errorCode = ofNullable(error.getCode()).orElse(-1);
            bldr.append("Error ").append(errorCode).append(":").append(error.getMsg());
        }
        return bldr.toString();
    }

    @Override
    public String toString() {
        final StringBuilder bldr = new StringBuilder(super.toString());
        if (error != null) bldr.append("; APIError=").append(error);
        return bldr.toString();
    }

    public String getDetailedMessage() {
        if (error == null && errorResponse == null) return "";
        StringBuilder bldr = new StringBuilder(super.toString());
        if (error != null) {
            final int errorCode = ofNullable(error.getCode()).orElse(-1);
            bldr.append(";").append("APIError: ").append(errorCode).append(":").append(error.getMsg()).append(";");
        }
        if (request != null) {
            bldr.append("Request: ").append(request.request()).append(";");
        }
        if (errorResponse != null) {
            bldr.append("Response: ").append(errorResponse);
        }
        return bldr.toString();
    }
}
