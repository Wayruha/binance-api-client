package com.binance.api.examples;

import com.binance.api.Constants;
import com.binance.api.client.MarginApiClient;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.WebSocketClient;
import com.binance.api.client.domain.account.OrderDetails;
import com.binance.api.client.domain.event.AccountUpdateEvent;
import com.binance.api.client.domain.event.EventType;

/**
 * User data stream endpoints examples.
 * <p>
 * It illustrates how to create a stream to obtain updates on a user account,
 * as well as update on trades/orders on a user account.
 */
public class MarginUserDataStreamExample {

    public static void main(String[] args) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(Constants.SPOT_API_KEY, Constants.SPOT_API_SECRET);
        MarginApiClient client = factory.newMarginRestClient();

        // First, we obtain a listenKey which is required to interact with the user data stream
        String listenKey = client.startUserDataStream();

        // Then, we open a new web socket client, and provide a callback that is called on every update
        WebSocketClient webSocketClient = factory.newWebSocketClient();

        // Listen for changes in the account
        webSocketClient.onUserDataUpdateEvent(listenKey, response -> {
            if (response.getEventType() == EventType.ACCOUNT_POSITION_UPDATE) {
                AccountUpdateEvent accountUpdateEvent = response.getAccountUpdateEvent();
                // Print new balances of every available asset
                System.out.println(accountUpdateEvent.getBalances());
            } else {
                OrderDetails orderUpdateEvent = response.getOrderTradeUpdateEvent();
                // Print details about an order/trade
                System.out.println(orderUpdateEvent);

                // Print original quantity
                System.out.println(orderUpdateEvent.getOriginalQuantity());

                // Or price
                System.out.println(orderUpdateEvent.getPrice());
            }
        });
        System.out.println("Waiting for events...");

        // We can keep alive the user data stream
        // client.keepAliveUserDataStream(listenKey);

        // Or we can invalidate it, whenever it is no longer needed
        // client.closeUserDataStream(listenKey);
    }
}
