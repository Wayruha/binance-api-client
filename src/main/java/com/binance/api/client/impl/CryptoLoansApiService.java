package com.binance.api.client.impl;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.account.request.LoanableAssetsDataResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CryptoLoansApiService {
    @GET("/sapi/v1/loan/loanable/data")
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    Call<LoanableAssetsDataResponse> getLoanableAssetsData(@Query("loanCoin") String loanCoin,
                                                           @Query("vipLevel") Integer vipLevel,
                                                           @Query("recvWindow") long recvWindow,
                                                           @Query("timestamp") long timestamp);
}
