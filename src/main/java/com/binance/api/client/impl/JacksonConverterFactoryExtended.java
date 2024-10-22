package com.binance.api.client.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

//Cloned from jackson library with adding params serialization
public class JacksonConverterFactoryExtended extends Converter.Factory {
    private final ObjectMapper paramsMapper;

    private JacksonConverterFactoryExtended(ObjectMapper paramsMapper) {
        this.paramsMapper = paramsMapper;
    }

    public static JacksonConverterFactoryExtended create() {
        return create(new ObjectMapper());
    }

    public static JacksonConverterFactoryExtended create(ObjectMapper paramsMapper) {
        if (paramsMapper == null) {
            throw new NullPointerException("paramsMapper == null");
        } else {
            return new JacksonConverterFactoryExtended(paramsMapper);
        }
    }

    @Override
    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        JavaType javaType = this.paramsMapper.getTypeFactory().constructType(type);
        ObjectWriter writer = this.paramsMapper.writerFor(javaType);
        return new JacksonStringConverter<>(writer);
    }

    static final class JacksonStringConverter<T> implements Converter<T, String> {
        private final ObjectWriter adapter;

        JacksonStringConverter(ObjectWriter adapter) {
            this.adapter = adapter;
        }

        public String convert(T value) throws IOException {
            final String jsonValue = this.adapter.writeValueAsString(value);
            if (jsonValue.startsWith("\"") && jsonValue.endsWith("\"")) {
                /* Strip enclosing quotes for json String types */
                return jsonValue.substring(1, jsonValue.length() - 1);
            } else {
                return jsonValue;
            }
        }
    }
}
