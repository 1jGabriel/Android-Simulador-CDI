package com.br.basedroid.data.retrofit

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://run.mocky.io/"
private const val CACHE_SIZE = 5 * 1024 * 1024L // 5 MB de cache

class RetrofitClient(
    private val application: Context
) {

    private val gson: Gson by lazy { GsonBuilder().setLenient().create() }

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .cache(cacheSize())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addNetworkInterceptor(CacheInterceptor)
            .build()
    }

    fun newInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private fun cacheSize(): Cache {
        return Cache(application.cacheDir, CACHE_SIZE)
    }
}
