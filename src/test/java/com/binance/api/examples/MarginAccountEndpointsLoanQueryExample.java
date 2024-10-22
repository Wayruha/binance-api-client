package com.binance.api.examples;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.MarginApiClient;
import com.binance.api.client.domain.account.Transaction;
import com.binance.api.client.domain.account.margin.MaxBorrowableQueryResult;
import com.binance.api.client.domain.account.RepayQueryResult;
import com.binance.api.client.domain.account.request.MarginLoanRequest;

import java.math.BigDecimal;

/**
 * Examples on how to get margin account information.
 */
public class MarginAccountEndpointsLoanQueryExample {

    public static void main(String[] args) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("YOUR_API_KEY", "YOUR_SECRET");
        MarginApiClient client = factory.newMarginRestClient();
        MaxBorrowableQueryResult usdt = client.queryMaxBorrowable("USDT");
        System.out.println(usdt.getAmount());
        MaxBorrowableQueryResult bnb = client.queryMaxBorrowable("BNB");
        System.out.println(bnb.getAmount());
        MarginLoanRequest request = new MarginLoanRequest(null, "USDT", new BigDecimal("310"),  false);
        Transaction borrowed = client.borrow(request);
        System.out.println(borrowed.getTranId());
        Transaction repaid = client.repay("USDT", false, null, "310");
        System.out.println(repaid);
        RepayQueryResult repayQueryResult = client.queryRepay("BNB", System.currentTimeMillis() - 1000);
        System.out.println(repayQueryResult);
    }
}
