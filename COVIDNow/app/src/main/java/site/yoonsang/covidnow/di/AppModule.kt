package site.yoonsang.covidnow.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import site.yoonsang.covidnow.network.CovidApi
import site.yoonsang.covidnow.network.HttpRequestInterceptor
import site.yoonsang.covidnow.network.KakaoApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCovidApi(): CovidApi =
        Retrofit.Builder()
            .baseUrl(CovidApi.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CovidApi::class.java)

    @Singleton
    @Provides
    fun provideLocationClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(HttpRequestInterceptor())
            .build()

    @Singleton
    @Provides
    fun provideKakaoApi(): KakaoApi =
        Retrofit.Builder()
            .baseUrl(KakaoApi.BASE_URL)
            .client(provideLocationClient())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KakaoApi::class.java)
}