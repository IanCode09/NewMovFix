package com.lexical.newmovfix.data.api

import com.lexical.newmovfix.utils.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {
    val retrofitClient: Retrofit.Builder by lazy {
        val logging =HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)

        Retrofit.Builder()
            .baseUrl(Credentials.LOGIN_URL)
            .client(okHttpClient.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val retrofitMovie: Retrofit.Builder by lazy {
        val logging =HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)

        Retrofit.Builder()
                .baseUrl(Credentials.BASE_URL)
                .client(okHttpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
    }

    val apiInterface: ApiInterface by lazy {
        retrofitClient.build().create(ApiInterface::class.java)
        retrofitMovie.build().create(ApiInterface::class.java)
    }

}