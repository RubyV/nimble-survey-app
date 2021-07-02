package com.ngocvu.example.di

import android.content.Context
import com.ngocvu.example.networking.SurveyAppApi
import com.ngocvu.example.networking.TokenAuthenticator
import com.ngocvu.example.utils.Prefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.reactivex.schedulers.Schedulers
import okhttp3.*

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    var logging =  HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    @OkHttpClientQualifier
    @JvmStatic

    internal fun provideOkHttpClient(prefs: Prefs): OkHttpClient {

        var builder =   OkHttpClient.Builder()
        .addNetworkInterceptor(logging)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder().addHeader("Authorization", "Bearer " + prefs.accessToken).build()
            chain.proceed(request) }
            .authenticator(TokenAuthenticator(prefs))
        .build()
        return builder
    }



    @Provides
    @BonuslinkApiStandard
    @Singleton
    fun provideRetrofit(@OkHttpClientQualifier okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(SurveyAppApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    @Provides
    @Singleton
    fun provideUnsplashApi(@BonuslinkRetrofitInterfaceStandard retrofit: Retrofit): SurveyAppApi =
        retrofit.create(SurveyAppApi::class.java)


    @Provides
    @Singleton
    fun providePrefs(@ApplicationContext context: Context): Prefs = Prefs(context)
}



annotation class BonuslinkRetrofitInterfaceStandard

annotation class BonuslinkApiStandard

annotation class OkHttpClientQualifier
