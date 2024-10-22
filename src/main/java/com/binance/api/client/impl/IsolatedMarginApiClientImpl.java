package com.binance.api.client.impl;

import com.binance.api.client.IsolatedMarginApiClient;

import static com.binance.api.client.config.BinanceApiConfig.getSpotBaseURL;
import static com.binance.api.client.config.BinanceApiConfig.getSpotTestnetUrl;
import static com.binance.api.client.impl.BinanceApiServiceGenerator.executeSync;

public class IsolatedMarginApiClientImpl implements IsolatedMarginApiClient {
    private final MarginApiService marginApiService;

    public IsolatedMarginApiClientImpl(String apiKey, String secret, boolean isTestnet) {
        final String url = isTestnet ? getSpotTestnetUrl() : getSpotBaseURL();
        marginApiService = BinanceApiServiceGenerator.createService(MarginApiService.class, url, apiKey, secret);
    }

    // user stream endpoints
    @Override
    public String startUserDataStream() {
        return executeSync(marginApiService.startIsolatedMarginUserDataStream()).toString();
    }

    @Override
    public void keepAliveUserDataStream(String listenKey) {
        executeSync(marginApiService.keepAliveIsolatedMarginUserDataStream(listenKey));
    }

    @Override
    public void closeUserDataStream(String listenKey) {
        executeSync(marginApiService.deleteIsolatedMarginUserDataStream(listenKey));
    }
}
