package com.binance.api.client;

import com.binance.api.client.impl.*;

import static com.binance.api.client.impl.BinanceApiServiceGenerator.getSharedClient;

/**
 * A factory for creating BinanceApi client objects.
 */
public class BinanceApiClientFactory {
    protected final String apiKey;
    protected final String secret;
    protected final boolean isTestnet;

    /**
     * Instantiates a new binance api client factory.
     *
     * @param apiKey    the API key
     * @param secret    the Secret
     * @param isTestnet Using testnet urls
     */
    public BinanceApiClientFactory(final String apiKey, final String secret, final boolean isTestnet) {
        this.apiKey = apiKey;
        this.secret = secret;
        this.isTestnet = isTestnet;
    }

    /**
     * LEFT here only for backward compatibility. Now, the constructor is used
     * New instance.
     */
    public static BinanceApiClientFactory newInstance(final String apiKey, final String secret) {
        return new BinanceApiClientFactory(apiKey, secret, false);
    }

    /**
     * New instance without authentication.
     *
     * @return the binance api client factory
     */
    public static BinanceApiClientFactory newInstance() {
        return new BinanceApiClientFactory(null, null, false);
    }

    /**
     * Creates a new synchronous/blocking REST client.
     */
    public ApiRestClient newRestClient() {
        return new SpotApiRestClientImpl(apiKey, secret, isTestnet);
    }

    /**
     * Creates a new synchronous/blocking REST client.
     */
    public FuturesRestClient newFuturesRestClient() {
        return new FuturesRestClientImpl(apiKey, secret, isTestnet);
    }

    /**
     * Creates a new asynchronous/non-blocking REST client.
     */
    public BinanceApiAsyncRestClient newAsyncRestClient() {
        return new BinanceApiAsyncRestClientImpl(apiKey, secret);
    }

    /**
     * Creates a new asynchronous/non-blocking Margin REST client.
     */
    public BinanceApiAsyncMarginRestClient newAsyncMarginRestClient() {
        return new BinanceApiAsyncMarginRestClientImpl(apiKey, secret, isTestnet);
    }

    /**
     * Creates a new synchronous/blocking Margin REST client.
     */
    public MarginApiClient newMarginRestClient() {
        return new MarginApiClientImpl(apiKey, secret, isTestnet);
    }

    public IsolatedMarginApiClient newIsolatedMarginRestClient() {
        return new IsolatedMarginApiClientImpl(apiKey, secret, isTestnet);
    }

    /**
     * Creates a new web socket client used for handling data streams.
     */
    public WebSocketClient newWebSocketClient() {
        return new SpotWebSocketClient(getSharedClient(), isTestnet);
    }

    /**
     * Creates a new web socket client used for handling data streams.
     */
    public WebSocketClient newFuturesWebSocketClient() {
        return new FuturesWebSocketClient(getSharedClient(), isTestnet);
    }

    /**
     * Creates a new synchronous/blocking Swap REST client.
     */
    public BinanceApiSwapRestClient newSwapRestClient() {
        return new BinanceApiSwapRestClientImpl(apiKey, secret);
    }

    public CryptoLoansApiClient newCryptoLoansApiClient() {
        return new CryptoLoansApiClientImpl(apiKey, secret, isTestnet);
    }
}
