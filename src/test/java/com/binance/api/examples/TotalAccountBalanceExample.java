package com.binance.api.examples;

import com.binance.api.Constants;
import com.binance.api.client.ApiRestClient;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.constant.Util;
import com.binance.api.client.domain.account.Account;
import com.binance.api.client.domain.account.AssetBalance;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Example how to get total of balances on your account
 */
public class TotalAccountBalanceExample {


    public static void main(String[] args) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(Constants.SPOT_API_KEY, Constants.SPOT_API_SECRET);
        ApiRestClient client = factory.newRestClient();

        // Get account balances
        Account account = client.getAccount(60_000L, System.currentTimeMillis());

        // Get total account balance in BTC (spot only)
        TotalAccountBalanceExample accountBalance = new TotalAccountBalanceExample();
        double totalBalanceInBTC = accountBalance.getTotalAccountBalance(client, account);
        System.out.println(totalBalanceInBTC);
        // Get total account balance in USDT (spot only)
        double totalBalanceInUSDT = totalBalanceInBTC * Double.parseDouble(client.getPrice("BTCUSDT").getPrice());
        System.out.println(totalBalanceInUSDT);

    }

    private void notEmptyAssets(List<AssetBalance> balances) {
        final List<AssetBalance> notEmpty = balances.stream().filter(
                b -> Double.parseDouble(b.getFree()) + Double.parseDouble(b.getLocked()) != 0d
        ).collect(Collectors.toList());
        System.out.println(notEmpty);
    }

    // Get total account balance in BTC (spot only)
    public double getTotalAccountBalance(ApiRestClient client, Account account) {
        double totalBalance = 0;
        final List<AssetBalance> balances = account.getBalances();
        for (AssetBalance balance : balances) {
            double free = Double.parseDouble(balance.getFree());
            double locked = Double.parseDouble(balance.getLocked());
            String ticker = balance.getAsset() + Util.BTC_TICKER;
            String tickerReverse = Util.BTC_TICKER + balance.getAsset();
            try {
                if (free + locked != 0) {
                    if (Util.isFiatCurrency(balance.getAsset())) {
                        double price = Double.parseDouble(client.getPrice(tickerReverse).getPrice());
                        double amount = (free + locked) / price;
                        totalBalance += amount;
                    } else {
                        double price = Double.parseDouble(client.getPrice(ticker).getPrice());
                        double amount = price * (free + locked);
                        totalBalance += amount;
                    }
                }
            } catch (Exception ex) {
                System.out.println("Can't get balance for asset " + balance.getAsset());
            }
        }

        return totalBalance;

    }


}
