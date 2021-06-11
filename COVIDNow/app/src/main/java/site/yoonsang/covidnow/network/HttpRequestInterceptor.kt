package site.yoonsang.covidnow.network

import okhttp3.Interceptor
import okhttp3.Response
import site.yoonsang.covidnow.BuildConfig

class HttpRequestInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("Authorization", BuildConfig.KAKAO_REST_KEY)
        return chain.proceed(builder.build())
    }
}