package ru.annachemic.reqres.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class CommonResponseMatchers {

    @SneakyThrows
    public static void checkStatusCodeStep(Response<?> response, int expectedCode) {
        assertThat(response.code(), equalTo(expectedCode));
        log.info("The response has a right status code");
    }

    public static <T> void checkNotEmptyBodyStep(Response<T> response, Class<T> type) {
        assertThat(response.body(), notNullValue(type));
        log.info("Response has not empty body");
    }

    public static <T> void checkErrorBodyStep(Response<T> response, String message) throws IOException {
        assert response.errorBody() != null;
        String errorBody = response.errorBody().string();
        log.info(errorBody);
        assertThat(errorBody, equalTo(message));
        log.info("Response has a right error message");
    }


}
