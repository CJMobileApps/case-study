package com.target.targetcasestudy.dagger.module

import android.content.Context
import com.target.targetcasestudy.BuildConfig
import com.target.targetcasestudy.dagger.DealsApplicationScope
import com.target.targetcasestudy.network.DealsApi
import com.target.targetcasestudy.network.NetworkConstants
import com.target.targetcasestudy.network.NetworkConstants.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.create
import java.io.File
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @DealsApplicationScope
    @Provides
    fun httpCacheDirectory(context: Context): File {
        return File(context.cacheDir, NetworkConstants.HTTP_CACHE_DIR)
    }

    @DealsApplicationScope
    @Provides
    fun cache(httpCacheDirectory: File): Cache {
        return Cache(httpCacheDirectory, NetworkConstants.HTTP_CACHE_SIZE)
    }

    @DealsApplicationScope
    @Provides
    fun networkCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())

            val cacheControl = CacheControl.Builder()
                .maxAge(1, TimeUnit.MINUTES)
                .build()

            response.newBuilder()
                .header(NetworkConstants.CACHE_CONTROL, cacheControl.toString()).build()
        }
    }

    @DealsApplicationScope
    @Provides
    fun loggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        if(BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }

        return loggingInterceptor
    }

    @DealsApplicationScope
    @Provides
    fun okHttpClient(
        cache: Cache,
        networkCacheInterceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .addNetworkInterceptor(networkCacheInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @DealsApplicationScope
    @Provides
    fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @DealsApplicationScope
    @Provides
    fun dealsApi(retrofit: Retrofit): DealsApi {
        return retrofit.create(DealsApi::class.java)
    }
}
