package com.osmar.tcc_mobile.Api;

import java.io.IOException;
import java.text.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ErrorUtils  {
    private RetrofitRequisicao requisicao = new RetrofitRequisicao();
    public static ApiError parseError(Response<?> response) {
        Converter<ResponseBody, ApiError> converter =
                requisicao.getRetrofit().retrofit()
                        .responseBodyConverter(ApiError.class, new Annotation[0]);

        ApiError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ApiError();
        }

        return error;
    }
}
