package com.binance.api.client.config;

/**
 * Configuration used for Binance operations.
 */
public class BinanceApiConfig {

    private static final String SPOT_API_URL = "https://api.binance.com";
    private static final String FUTURES_API_URL = "https://fapi.binance.com";

    public static final String FUTURES_WS_URL = "wss://fstream.binance.com/ws";
    private static final String SPOT_WS_URL = "wss://stream.binance.com:9443/ws";

    /**
     * Spot Test Network URL.
     */
    private static final String SPOT_TESTNET_DOMAIN = "testnet.binance.vision";
    private static final String FUTURES_API_TESTNET_DOMAIN = "testnet.binancefuture.com";
    private static final String FUTURES_WS_TESTNET_DOMAIN = "stream.binancefuture.com";

    private static final String WS_URL_FORMAT = "wss://%s/ws";
    private static final String HTTP_URL_FORMAT = "https://%s";

    /**
     * SPOT REST API URL.
     */
    public static String getSpotBaseURL() {
        return SPOT_API_URL;
    }

    public static String getFuturesBaseURL() {
        return FUTURES_API_URL;
    }


    /**
     * SPOT Streaming API.
     */
    public static String getSpotStreamUrl() {
        return SPOT_WS_URL;
    }

    public static String getFuturesStreamUrl() {
        return FUTURES_WS_URL;
    }

    /**
     * Asset info base URL.
     */
    public static String getAssetInfoApiBaseUrl() {
        return "https://binance.com/";
    }

    /**
     * Spot Test Network API base URL.
     */
    public static String getSpotTestnetUrl() {
        return String.format(HTTP_URL_FORMAT, SPOT_TESTNET_DOMAIN);
    }

    /**
     * Streaming Spot Test Network base URL.
     */
    public static String getSpotStreamTestnetUrl() {
        return String.format(WS_URL_FORMAT, SPOT_TESTNET_DOMAIN);
    }

    public static String getFuturesTestnetUrl() {
        return String.format(HTTP_URL_FORMAT, FUTURES_API_TESTNET_DOMAIN);
    }

    public static String getFuturesStreamTestnetUrl() {
        return String.format(WS_URL_FORMAT, FUTURES_WS_TESTNET_DOMAIN);
    }
}
