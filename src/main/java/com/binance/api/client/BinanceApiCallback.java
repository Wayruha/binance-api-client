package com.binance.api.client;

/**
 * BinanceApiCallback is a functional interface used together with the BinanceApiAsyncClient to provide a non-blocking REST client.
 *
 * @param <T> the return type from the callback
 */
@FunctionalInterface
public interface BinanceApiCallback<T> {
    /**
     * Called whenever a response comes back from the Binance API.
     *
     * @param response the expected response object
     */
    void onResponse(T response);

    /**
     * Called whenever an uncaught failure occurs.
     *
     * @param cause the cause of the failure
     */
    default void onFailure(Throwable cause) {
    }

    /**
     * Called whenever a handled error occurs.
     * Not a fatal. Just to inform the caller and log the error
     *
     * @param cause the cause of the failure
     */
    default void onError(Throwable cause) {
    }
}
