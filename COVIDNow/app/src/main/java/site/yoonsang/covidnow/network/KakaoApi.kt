package site.yoonsang.covidnow.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import site.yoonsang.covidnow.model.LocationResponse

interface KakaoApi {

    companion object {
        const val BASE_URL = "https://dapi.kakao.com/"
    }

    @GET("/v2/local/search/keyword.json")
    fun getLocationResponse(
        @Query("query") query: String = "신종코로나바이러스감염증선별진료소",
        @Query("x") x: String?,
        @Query("y") y: String?,
        @Query("radius") radius: Int? = 5000,
        @Query("page") page: Int? = 1,
        @Query("size") size: Int? = 15,
        @Query("sort") sort: String? = "distance"
    ): Response<LocationResponse>
}