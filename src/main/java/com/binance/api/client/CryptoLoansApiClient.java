package com.binance.api.client;

import com.binance.api.client.domain.account.request.LoanableAssetsDataRequest;
import com.binance.api.client.domain.account.request.LoanableAssetsDataResponse;

public interface CryptoLoansApiClient {
    LoanableAssetsDataResponse getLoanableAssetsData(LoanableAssetsDataRequest request);
}
