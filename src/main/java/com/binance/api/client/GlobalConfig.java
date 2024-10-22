package com.binance.api.client;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;

@Slf4j
public class GlobalConfig {
    public static boolean USE_LOGGING = false;
    public static boolean USE_TESTNET = false;

    //should be provided from the outside
    public static HttpLoggingInterceptor.Logger DEFAULT_LOGGER = System.out::println;
    public static HttpLoggingInterceptor.Logger httpLogger = DEFAULT_LOGGER;
    public static org.slf4j.Logger webSocketLogger = log;
    public static Interceptor DEFAULT_INTERCEPTOR = chain -> chain.proceed(chain.request());
    public static Interceptor apiLimitInterceptor = DEFAULT_INTERCEPTOR;

    public static long SERVER_TIME_WINDOW_DELAY_MS = 15_000;
    /**
     * Get timestamp in millis
     */
    public static long getCurrentTime() {
        //TODO: Is this compatible with delayed api calls?
        return System.currentTimeMillis() - SERVER_TIME_WINDOW_DELAY_MS;
    }
}
