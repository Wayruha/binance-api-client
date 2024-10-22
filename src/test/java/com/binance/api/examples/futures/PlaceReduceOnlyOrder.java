package com.binance.api.examples.futures;

import com.binance.api.Constants;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.FuturesRestClient;
import com.binance.api.client.domain.OrderSide;
import com.binance.api.client.domain.OrderType;
import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.account.FuturesOrder;
import com.binance.api.client.domain.account.NewOrderResponseType;

public class PlaceReduceOnlyOrder {
    private static FuturesRestClient client;

    public static void main(String[] args) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(Constants.SPOT_API_KEY, Constants.SPOT_API_SECRET);
        client = factory.newFuturesRestClient();
        final String symbol = "BTCUSDT";
        final double quantity = 0.001;
        final OrderSide side = OrderSide.BUY;
        final OrderType type = OrderType.MARKET;
        final TimeInForce timeInForce = TimeInForce.GTC;
        final boolean reduceOnly = true;

        //FuturesOrder placedOrder = placePosition(symbol, 2 * quantity, side, type);
        FuturesOrder placedOrder = closePosition(symbol, 2 * quantity, side, type);
        System.out.println(placedOrder);
    }

    private static FuturesOrder placePosition(String symbol, double _quantity, OrderSide side, OrderType type) {
        String quantity = String.valueOf(_quantity);
        FuturesOrder newOrder = new FuturesOrder(symbol, side, type, quantity);
        return client.newOrder(newOrder, NewOrderResponseType.FULL);
    }

    private static FuturesOrder closePosition(String symbol, double _quantity, OrderSide originalSide, OrderType type) {
        String quantity = String.valueOf(_quantity);
        FuturesOrder newOrder = new FuturesOrder(symbol, originalSide.oppositeSide(), type, quantity);
        newOrder.setReduceOnly(true);
        return client.newOrder(newOrder, NewOrderResponseType.FULL);
    }

}
