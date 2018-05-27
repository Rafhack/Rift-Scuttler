package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.remote

import android.text.TextUtils
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.BuildConfig
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.RiftScuttlerApplication
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.schedulers.Schedulers
import java.io.File
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * Created by Marcel on 19/09/2016
 */
object ServiceGenerator {

    //region FIELDS
    private const val DEFAULT_TIMEOUT: Long = 60
    private const val CACHE_SIZE: Long = 10 * 1024 * 1024
    private const val MAX_STALE = 60 * 60 * 24 * 28
    private const val MAX_AGE = 60 * 60
    private var authorization: String? = null
    private var useForcedCache: Boolean = false
    //endregion

    //region METHODS

    //region PUBLIC METHODS
    fun <S> createService(serviceClass: Class<S>, useForcedCache: Boolean = false): S {
        authorization = BuildConfig.API_KEY
        ServiceGenerator.useForcedCache = useForcedCache

        val cacheDirectory = File(RiftScuttlerApplication.application?.externalCacheDir?.absolutePath, "OKHttpCache")

        val builder = OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(defaultInterceptor)
                .addInterceptor(loggingInterceptor)

        if (useForcedCache) {
            try {
                val cache = Cache(cacheDirectory, CACHE_SIZE)
                builder.cache(cache)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .registerTypeAdapter(Calendar::class.java, CalendarSerializer())
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL.format(RiftScuttlerApplication.region?.endPoint))
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()

        return retrofit.create(serviceClass)
    }
    //endregion

    //region PRIVATE METHODS
    private val defaultInterceptor: Interceptor
        get() = Interceptor { chain ->

            val builder = chain.request().newBuilder()

            if (!TextUtils.isEmpty(authorization))
                builder.addHeader("X-Riot-Token", authorization!!)

            val request = builder.build()
            val resp: Response = chain.proceed(request)

            if (useForcedCache)
                resp.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=$MAX_STALE, max-age=$MAX_AGE")
                        .build()
            else resp
        }

    private val loggingInterceptor: Interceptor
        get() {
            val logging = HttpLoggingInterceptor()
            logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            return logging
        }
    //endregion

    //endregion
}