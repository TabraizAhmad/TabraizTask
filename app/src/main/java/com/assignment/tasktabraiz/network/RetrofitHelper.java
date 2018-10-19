package com.assignment.tasktabraiz.network;

import com.assignment.tasktabraiz.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.assignment.tasktabraiz.common.Constants.BASE_URL;


public class RetrofitHelper {

    public WebService getWebService() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl newUrl = originalHttpUrl.newBuilder()
                        .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                        .build();

                Request.Builder requestBuilder = original.newBuilder()
                        .url(newUrl);
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit restClient = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return restClient.create(WebService.class);
    }

}
