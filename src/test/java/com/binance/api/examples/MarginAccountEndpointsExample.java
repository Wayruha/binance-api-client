package com.binance.api.examples;

import com.binance.api.Constants;
import com.binance.api.client.ApiRestClient;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.MarginApiClient;
import com.binance.api.client.domain.TransferType;
import com.binance.api.client.domain.account.margin.MarginAccount;
import com.binance.api.client.domain.account.Transaction;
import com.binance.api.client.domain.account.Trade;
import com.binance.api.client.domain.account.request.MarginLoanRequest;

import java.math.BigDecimal;
import java.util.List;

/**
 * Examples on how to get margin account information.
 */
public class MarginAccountEndpointsExample {

  public static void main(String[] args) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(Constants.SPOT_API_KEY, Constants.SPOT_API_SECRET);
    MarginApiClient client = factory.newMarginRestClient();
    ApiRestClient spotClient = factory.newRestClient();

    // Get account balances
    MarginAccount marginAccount = client.getAccount();
    System.out.println(marginAccount.getUserAssets());
    System.out.println(marginAccount.getAssetBalance("ETH"));
    System.out.println(marginAccount.getMarginLevel());

    // Get list of trades
    List<Trade> myTrades = spotClient.getMyTrades("NEOETH");
    System.out.println(myTrades);

    // Transfer, borrow, repay
    MarginLoanRequest request = new MarginLoanRequest();
    request.setAsset("USDT");
    request.setAmount(new BigDecimal("1"));
    Transaction spotToMargin = client.transfer("USDT", "1", TransferType.SPOT_TO_MARGIN);
    System.out.println(spotToMargin.getTranId());
    Transaction borrowed = client.borrow(request);
    System.out.println(borrowed.getTranId());
    Transaction repayed = client.repay("USDT", false, null,  "1");
    System.out.println(repayed.getTranId());
    Transaction marginToSpot = client.transfer("USDT", "1", TransferType.MARGIN_TO_SPOT);
    System.out.println(marginToSpot.getTranId());
  }
}
