package com.binance.api.examples;

import com.binance.api.Constants;
import com.binance.api.client.MarginApiClient;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.account.margin.MarginNewOrderResponse;
import com.binance.api.client.domain.account.NewOrderResponseType;
import com.binance.api.client.domain.account.Order;
import com.binance.api.client.domain.account.request.*;
import com.binance.api.client.exception.BinanceApiException;

import java.util.List;

import static com.binance.api.client.domain.account.margin.MarginNewOrder.limitBuy;

/**
 * Examples on how to place orders, cancel them, and query account information.
 */
public class MarginOrdersExample {

    public static void main(String[] args) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(Constants.SPOT_API_KEY, Constants.SPOT_API_SECRET);
        MarginApiClient client = factory.newMarginRestClient();

        // Getting list of open orders
        List<Order> openOrders = client.getOpenOrders(new OrderRequest("LINKETH"));
        System.out.println(openOrders);

        // Get status of a particular order
        Order order = client.getOrderStatus(new MarginOrderStatusRequest("LINKETH", 751698L, true));
        System.out.println(order);

        // Canceling an order
        try {
            CancelOrderResponse cancelOrderResponse = client.cancelOrder(new CancelMarginOrderRequest("LINKETH", 756762l, true));
            System.out.println(cancelOrderResponse);
        } catch (BinanceApiException e) {
            System.out.println(e.getError().getMsg());
        }

        // Placing a real LIMIT order
        MarginNewOrderResponse newOrderResponse = client.newOrder(limitBuy("LINKETH", false, TimeInForce.GTC, "1000", null, "0.0001").newOrderRespType(NewOrderResponseType.FULL));
        System.out.println(newOrderResponse);
    }

}
