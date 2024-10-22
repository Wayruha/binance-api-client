package com.binance.api.examples;

import com.binance.api.client.BinanceApiAsyncMarginRestClient;
import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.domain.TransferType;

/**
 * Examples on how to get margin account information asynchronously.
 */
public class MarginAccountEndpointsExampleAsync {

  public static void main(String[] args) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("YOUR_API_KEY", "YOUR_SECRET");
    BinanceApiAsyncMarginRestClient client = factory.newAsyncMarginRestClient();
    BinanceApiAsyncRestClient spotClient = factory.newAsyncRestClient();

    // Get account balances
    client.getAccount(marginAccount -> {
      System.out.println(marginAccount.getUserAssets());
      System.out.println(marginAccount.getAssetBalance("ETH"));
      System.out.println(marginAccount.getMarginLevel());
    });

    // Get list of trades
    spotClient.getMyTrades("NEOETH", myTrades -> {
      System.out.println(myTrades);
    });

    // Transfer, borrow, repay
    client.transfer("USDT", "1", TransferType.SPOT_TO_MARGIN, transaction -> System.out.println(transaction.getTranId()));
    client.borrow("USDT", false, null, "1", transaction -> System.out.println(transaction.getTranId()));
    client.repay("USDT", false, null,  "1", transaction -> System.out.println(transaction.getTranId()));
    client.transfer("USDT", "1", TransferType.MARGIN_TO_SPOT, transaction -> System.out.println(transaction.getTranId()));
  }
}
