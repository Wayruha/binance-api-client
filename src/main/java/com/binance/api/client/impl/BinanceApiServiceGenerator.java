package com.binance.api.client.impl;

import com.binance.api.client.*;
import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.exception.*;
import com.binance.api.client.security.AuthenticationInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.commons.lang3.StringUtils;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.*;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import static com.binance.api.client.config.BinanceApiConfig.getSpotBaseURL;

/**
 * Generates a Binance API implementation based on @see {@link BinanceApiService}.
 */
public class BinanceApiServiceGenerator {
    private static final OkHttpClient sharedClient;
    private static final Converter.Factory bodyConverterFactory = JacksonConverterFactory.create();
    private static final Converter.Factory paramsConverterFactory = JacksonConverterFactoryExtended.create();
    private static final boolean USE_LOGGING = GlobalConfig.USE_LOGGING;
    @Setter
    private static ObjectMapper mapper = new ObjectMapper();
    @SuppressWarnings("unchecked")
    private static final Converter<ResponseBody, BinanceApiError> errorBodyConverter =
            (Converter<ResponseBody, BinanceApiError>) bodyConverterFactory.responseBodyConverter(
                    BinanceApiError.class, new Annotation[0], null);

    static {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequestsPerHost(500);
        dispatcher.setMaxRequests(500);
        sharedClient = new OkHttpClient.Builder()
                               .dispatcher(dispatcher)
                               .pingInterval(5, TimeUnit.MINUTES) //every X mins it sends ping req from the client. Just to see if server is alive
                               .build();
    }

    //left for a backward compatibility
    public static BinanceApiService createSpotService(String apiKey, String secret) {
        return createService(BinanceApiService.class, getSpotBaseURL(), apiKey, secret);
    }

    /**
     * Create a Binance API service.
     *
     * @param serviceClass the type of service.
     * @param apiKey       Binance API key.
     * @param secret       Binance secret.
     *
     * @return a new implementation of the API endpoints for the Binance API service.
     */
    public static <S> S createService(Class<S> serviceClass, String baseUrl, String apiKey, String secret) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                                                   .baseUrl(baseUrl)
                                                   .addConverterFactory(bodyConverterFactory)
                                                   .addConverterFactory(paramsConverterFactory);

        if (StringUtils.isEmpty(apiKey) || StringUtils.isEmpty(secret)) {
            retrofitBuilder.client(sharedClient);
        } else {
            // `adaptedClient` will use its own authInterceptor, but share thread pool etc with the 'parent' client
            AuthenticationInterceptor authInterceptor = new AuthenticationInterceptor(apiKey, secret);
            OkHttpClient.Builder clientBuilder = sharedClient.newBuilder()
                                                         .addInterceptor(authInterceptor)
                                                         //TODO Does not work on unsecured endpoints
                                                         .addNetworkInterceptor(GlobalConfig.apiLimitInterceptor);
            if (USE_LOGGING) {
                Objects.requireNonNull(GlobalConfig.httpLogger, "HTTP Logger is null!");
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(GlobalConfig.httpLogger);
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                clientBuilder.addInterceptor(loggingInterceptor);
            }
            OkHttpClient adaptedClient = clientBuilder.build();
            retrofitBuilder.client(adaptedClient);
        }
        Retrofit retrofit = retrofitBuilder.build();
        return retrofit.create(serviceClass);
    }

    /**
     * Execute a REST call and block until the response is received.
     */
    public static <T> T executeSync(Call<T> call) {
        final Response<T> response;
        String errorBody = null;
        try {
            response = call.execute();
            if (response.isSuccessful()) {
                final T body = response.body();
                if (body != null) {
                    GlobalConfig.httpLogger.log("<-- Response: " + body);
                }
                return body;
            } else {
                errorBody = response.errorBody().string();
                GlobalConfig.httpLogger.log("<-- Fail: " + errorBody);
                BinanceApiError apiError = getBinanceApiError(errorBody);
                throw buildAPIException(apiError, call, errorBody);
            }
        } catch (IOException e) {
            final BinanceApiException ex = new BinanceApiException("HTTP call failed. ErrorBody=" + errorBody, e);
            ex.setErrorResponse(errorBody);
            throw ex;
        }
    }

    public static <T> Response<T> executeSyncWrapped(Call<T> call, Consumer<Response<T>> resultCallback) {
        final Response<T> response;
        String errorBody = null;
        try {
            response = call.execute();
            resultCallback.accept(response);
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    GlobalConfig.httpLogger.log("<-- Response: " + response.body());
                }
                return response;
            } else {
                errorBody = response.errorBody().string();
                GlobalConfig.httpLogger.log("<-- Fail: " + errorBody);
                BinanceApiError apiError = getBinanceApiError(errorBody);
                throw buildAPIException(apiError, call, errorBody);
            }
        } catch (IOException e) {
            final BinanceApiException ex = new BinanceApiException("HTTP call failed. ErrorBody=" + errorBody, e);
            ex.setErrorResponse(errorBody);
            throw ex;
        }
    }

    public static BinanceApiException buildAPIException(BinanceApiError error, Call request, String errorResponse) {
        if (error != null && error.getCode() != null && error.getCode() == BinanceApiConstants.API_KEY_ERROR_CODE) {
            return new InvalidAPIKeyException(error, request, errorResponse);
        }
        return new BinanceApiException(error, request, errorResponse);
    }

    /**
     * Extracts and converts the response error body into an object.
     */
    public static BinanceApiError getBinanceApiError(Response<?> response) throws IOException, BinanceApiException {
        return errorBodyConverter.convert(response.errorBody());
    }

    public static BinanceApiError getBinanceApiError(String errorResponse) throws IOException, BinanceApiException {
        return mapper.readValue(errorResponse, BinanceApiError.class);
    }

    /**
     * Returns the shared OkHttpClient instance.
     */
    public static OkHttpClient getSharedClient() {
        return sharedClient;
    }
}
