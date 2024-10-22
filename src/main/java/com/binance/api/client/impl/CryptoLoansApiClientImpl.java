package com.binance.api.client.impl;

import com.binance.api.client.CryptoLoansApiClient;
import com.binance.api.client.GlobalConfig;
import com.binance.api.client.domain.account.request.LoanableAssetsDataRequest;
import com.binance.api.client.domain.account.request.LoanableAssetsDataResponse;

import static com.binance.api.client.config.BinanceApiConfig.*;
import static com.binance.api.client.constant.BinanceApiConstants.DEFAULT_RECEIVING_WINDOW;
import static com.binance.api.client.impl.BinanceApiServiceGenerator.*;

public class CryptoLoansApiClientImpl implements CryptoLoansApiClient {
    protected final CryptoLoansApiService cryptoLoansApiService;

    public CryptoLoansApiClientImpl(String apiKey, String secret, boolean isTestnet) {
        final String url = isTestnet ? getSpotTestnetUrl() : getSpotBaseURL();
        cryptoLoansApiService = createService(CryptoLoansApiService.class, url, apiKey, secret);
    }
    @Override
    public LoanableAssetsDataResponse getLoanableAssetsData(LoanableAssetsDataRequest request) {
        return executeSync(cryptoLoansApiService.getLoanableAssetsData(request.getLoanCoin(), request.getVipLevel(), DEFAULT_RECEIVING_WINDOW, GlobalConfig.getCurrentTime()));
    }
}
