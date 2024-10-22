package com.binance.api.examples.futures;

import com.binance.api.Constants;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.FuturesRestClient;
import com.binance.api.client.domain.account.FuturesPosition;
import com.binance.api.client.domain.account.request.FuturesAccountInfo;

import java.util.List;
import java.util.stream.Collectors;

public class FuturesAccountBalance {
    public static void main(String[] args) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(Constants.SPOT_API_KEY, Constants.SPOT_API_SECRET);
        FuturesRestClient client = factory.newFuturesRestClient();
        final FuturesAccountInfo accountInfo = client.getAccountInfo();
        System.out.println(accountInfo);
        final List<FuturesPosition> positions = accountInfo.getPositions().stream()
                .filter(p -> Double.parseDouble(p.getPositionAmt()) != 0)
                .collect(Collectors.toList());
        System.out.println("Assets:");
        System.out.println(accountInfo.getAssets());
        System.out.println("Open positions:");
        System.out.println(positions);
    }
}
