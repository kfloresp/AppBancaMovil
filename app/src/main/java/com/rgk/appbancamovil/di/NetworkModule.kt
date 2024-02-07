package com.rgk.appbancamovil.di
import com.rgk.appbancamovil.data.source.remote.network.AccountsDetailClient
import com.rgk.appbancamovil.data.source.remote.network.HomeAccountsClient
import com.rgk.appbancamovil.data.source.remote.network.HomeLoginClient
import com.rgk.appbancamovil.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

     @Singleton
     @Provides
     fun provideRetrofit(): Retrofit {
         val interceptorLog = HttpLoggingInterceptor()
         interceptorLog.setLevel(HttpLoggingInterceptor.Level.BODY)
         val logger: OkHttpClient = OkHttpClient.Builder()
             .readTimeout(60, TimeUnit.SECONDS)
             .connectTimeout(60, TimeUnit.SECONDS)
             .connectTimeout(10, TimeUnit.SECONDS)
             .addInterceptor(interceptorLog)
             .build()
         return Retrofit.Builder()
             .baseUrl(Constants.baseUrl)
             .client(logger)
             .addConverterFactory(GsonConverterFactory.create())
             .build()
     }

    @Singleton
    @Provides
    fun provideHomeClient(retrofit: Retrofit): HomeLoginClient {
        return retrofit.create(HomeLoginClient::class.java)
    }

    @Singleton
    @Provides
    fun provideHomeAccountsClient(retrofit: Retrofit): HomeAccountsClient {
        return retrofit.create(HomeAccountsClient::class.java)
    }

    @Singleton
    @Provides
    fun provideAccountsDetailClient(retrofit: Retrofit): AccountsDetailClient {
        return retrofit.create(AccountsDetailClient::class.java)
    }

}