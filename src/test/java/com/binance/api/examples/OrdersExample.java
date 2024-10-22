package com.binance.api.examples;

import com.binance.api.Constants;
import com.binance.api.client.ApiRestClient;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.domain.account.Order;
import com.binance.api.client.domain.account.request.AllOrdersRequest;
import com.binance.api.client.domain.account.request.OrderRequest;
import com.binance.api.client.domain.account.request.OrderStatusRequest;

import java.util.List;

/**
 * Examples on how to place orders, cancel them, and query account information.
 */
public class OrdersExample {

  public static void main(String[] args) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(Constants.SPOT_API_KEY, Constants.SPOT_API_SECRET);
    ApiRestClient client = factory.newRestClient();
    final String symbol = "BTCUSDT";

    // Getting list of open orders
    List<Order> openOrders = client.getOpenOrders(new OrderRequest(symbol));
    System.out.println(openOrders);

    // Getting list of all orders with a limit of 10
    List<Order> allOrders = client.getAllOrders(new AllOrdersRequest(symbol).limit(10));
    System.out.println(allOrders);

    // Get status of a particular order
    Order order = client.getOrderStatus(new OrderStatusRequest(symbol, 751698L));
    System.out.println(order);

    // Canceling an order
   /* try {
      CancelOrderResponse cancelOrderResponse = client.cancelOrder(new CancelOrderRequest("LINKETH", 756762l));
      System.out.println(cancelOrderResponse);
    } catch (BinanceApiException e) {
      System.out.println(e.getError().getMsg());
    }

    // Placing a test LIMIT order
    client.newOrderTest(limitBuy("LINKETH", TimeInForce.GTC, "1000", "0.0001"));

    // Placing a test MARKET order
    client.newOrderTest(marketBuy("LINKETH", "1000"));

    // Placing a real LIMIT order
    NewOrderResponse newOrderResponse = client.newOrder(limitBuy("LINKETH", TimeInForce.GTC, "1000", "0.0001").newOrderRespType(NewOrderResponseType.FULL));
    System.out.println(newOrderResponse);*/
  }

}
