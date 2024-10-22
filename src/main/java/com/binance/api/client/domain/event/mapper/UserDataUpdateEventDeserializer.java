package com.binance.api.client.domain.event.mapper;

import com.binance.api.client.domain.account.FuturesPosition;
import com.binance.api.client.domain.event.*;
import com.binance.api.client.exception.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.binance.api.client.domain.AccountUpdateReasonType.MARGIN_TYPE_CHANGE;
import static com.binance.api.client.domain.event.EventType.*;

/**
 * Custom deserializer for a User Data stream event, since the API can return four different responses in this stream.
 *
 * @see UserDataUpdateEvent
 */
public class UserDataUpdateEventDeserializer extends JsonDeserializer<UserDataUpdateEvent> {

    private ObjectMapper mapper;

    @Override
    public UserDataUpdateEvent deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {

        if (mapper == null) {
            mapper = new ObjectMapper();
        }

        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);
        String json = node.toString();

        final String eventTypeId = node.get("e").asText();
        final Long eventTime = node.get("E").asLong();
        final Long transaction = node.has("T") ? node.get("T").asLong() : null;

        EventType eventType = EventType.fromEventTypeId(eventTypeId);
        UserDataUpdateEvent userDataUpdateEvent = new UserDataUpdateEvent();
        userDataUpdateEvent.setEventTime(eventTime);
        userDataUpdateEvent.setEventType(eventType);
        userDataUpdateEvent.setTransaction(transaction);

        switch (eventType) {
            case ACCOUNT_POSITION_UPDATE:
                AccountUpdateEvent accountUpdateEvent = parseJson(json, AccountUpdateEvent.class, mapper);
                setDefaults(accountUpdateEvent, eventType, eventTime, transaction == null ? accountUpdateEvent.getLastUpdated() : transaction);
                userDataUpdateEvent.setAccountUpdateEvent(accountUpdateEvent);
                break;
            case BALANCE_UPDATE:
                BalanceUpdateEvent balanceUpdateEvent = parseJson(json, BalanceUpdateEvent.class, mapper);
                setDefaults(balanceUpdateEvent, BALANCE_UPDATE, eventTime, transaction);
                userDataUpdateEvent.setBalanceUpdateEvent(balanceUpdateEvent);
                break;
            case ORDER_TRADE_UPDATE:
                OrderTradeUpdateEvent orderTradeUpdateEvent = parseJson(json, OrderTradeUpdateEvent.class, mapper);
                setDefaults(orderTradeUpdateEvent, ORDER_TRADE_UPDATE, eventTime, transaction);
                userDataUpdateEvent.setOrderTradeUpdateEvent(orderTradeUpdateEvent);
                break;
            case FUTURES_ORDER_TRADE_UPDATE:
                String eventJson = node.get("o").toString();
                FuturesTradeEvent futuresTradeEvent = parseJson(eventJson, FuturesTradeEvent.class, mapper);
                setDefaults(futuresTradeEvent, FUTURES_ORDER_TRADE_UPDATE, eventTime, transaction);
                userDataUpdateEvent.setFuturesTradeEvent(futuresTradeEvent);
                break;
            case FUTURES_ACCOUNT_UPDATE:
                String updateDataJson = node.get("a").toString();
                FuturesAccountUpdateEvent updateData = parseJson(updateDataJson, FuturesAccountUpdateEvent.class, mapper);
                // if it's MarginChange event then we create AccConfigEvent instead of original one (AccountUpdateEvent).
                switch (updateData.getReasonType()) {
                    case MARGIN_TYPE_CHANGE:
                        final List<FuturesPosition> pairsInvolved = updateData.getPositions().stream().map(FuturesPosition::new).collect(Collectors.toList());
                        final FuturesAccConfigEvent.MarginDetails marginDetails = new FuturesAccConfigEvent.MarginDetails(pairsInvolved);
                        final FuturesAccConfigEvent accConfigEvent = new FuturesAccConfigEvent(null, null, marginDetails);
                        setDefaults(accConfigEvent, ACCOUNT_CONFIG_UPDATE, eventTime, transaction);
                        userDataUpdateEvent.setEventType(ACCOUNT_CONFIG_UPDATE);
                        userDataUpdateEvent.setFuturesAccConfigEvent(accConfigEvent);
                        break;
                    case MARGIN_TRANSFER:
                        //TODO what to do here?
                        break;
                    default:
                        setDefaults(updateData, FUTURES_ACCOUNT_UPDATE, eventTime, transaction);
                        userDataUpdateEvent.setFuturesAccountUpdateEvent(updateData);
                }
                if (updateData.getReasonType() == MARGIN_TYPE_CHANGE) {
                    final List<FuturesPosition> pairsInvolved = updateData.getPositions().stream().map(FuturesPosition::new).collect(Collectors.toList());
                    final FuturesAccConfigEvent.MarginDetails marginDetails = new FuturesAccConfigEvent.MarginDetails(pairsInvolved);
                    final FuturesAccConfigEvent accConfigEvent = new FuturesAccConfigEvent(null, null, marginDetails);
                    setDefaults(accConfigEvent, ACCOUNT_CONFIG_UPDATE, eventTime, transaction);
                    userDataUpdateEvent.setEventType(ACCOUNT_CONFIG_UPDATE);
                    userDataUpdateEvent.setFuturesAccConfigEvent(accConfigEvent);
                } else {
                    setDefaults(updateData, FUTURES_ACCOUNT_UPDATE, eventTime, transaction);
                    userDataUpdateEvent.setFuturesAccountUpdateEvent(updateData);
                }
                break;
            case ACCOUNT_CONFIG_UPDATE:
                FuturesAccConfigEvent accCfgEvent = parseJson(json, FuturesAccConfigEvent.class, mapper);
                setDefaults(accCfgEvent, ACCOUNT_CONFIG_UPDATE, eventTime, transaction != null ? transaction : accCfgEvent.getEventTime());
                userDataUpdateEvent.setFuturesAccConfigEvent(accCfgEvent);
                break;
            case MARGIN_CALL:
                MarginCallEvent mrgnCallEvent = parseJson(json, MarginCallEvent.class, mapper);
                setDefaults(mrgnCallEvent, MARGIN_CALL, eventTime, transaction);
                userDataUpdateEvent.setMarginCallEvent(mrgnCallEvent);
                break;
            case COIN_SWAP_ORDER:
                String swapJson = node.get("c").toString();
                CoinSwapEvent coinSwapEvent = parseJson(swapJson, CoinSwapEvent.class, mapper);
                setDefaults(coinSwapEvent, COIN_SWAP_ORDER, eventTime, transaction);
                userDataUpdateEvent.setCoinSwapEvent(coinSwapEvent);
                break;
            case OCO_TRADE_UPDATE:
                OCOTradeEvent ocoEvent = parseJson(json, OCOTradeEvent.class, mapper);
                setDefaults(ocoEvent, OCO_TRADE_UPDATE, eventTime, transaction != null ? transaction : ocoEvent.getEventTime());
                userDataUpdateEvent.setOcoTradeEvent(ocoEvent);
                break;
            case LISTEN_KEY_EXPIRED:
                //throw new UnsupportedEventException("ListenKey-expiration should not normally happen. JSON=" + json);
                userDataUpdateEvent.setTransaction(eventTime);
                break;
            default:
                throw new UnsupportedEventException("Unrecognized user data update event type id: " + eventTypeId + ". JSON=" + json);
        }
        return userDataUpdateEvent;
    }

    private static void setDefaults(AbstractEvent event, EventType type, Long eventTime, Long transaction) {
        event.setEventType(type);
        event.setEventTime(eventTime);
        event.setTransaction(transaction);
    }

    public <T> T parseJson(String json, Class<T> clazz, ObjectMapper mapper) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new BinanceApiException(e);
        }
    }
}
