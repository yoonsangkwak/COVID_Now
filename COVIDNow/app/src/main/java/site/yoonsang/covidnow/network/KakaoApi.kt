package site.yoonsang.covidnow.network

import retrofit2.http.GET
import retrofit2.http.Query
import site.yoonsang.covidnow.model.LocationResponse
import site.yoonsang.covidnow.util.Constants

interface KakaoApi {

    companion object {
        const val BASE_URL = "https://dapi.kakao.com/"
    }

    @GET("/v2/local/search/keyword.json")
    suspend fun getLocationResponse(
        @Query("query") query: String = "신종코로나바이러스감염증선별진료소",
        @Query("x") x: String? = null,
        @Query("y") y: String? = null,
        @Query("radius") radius: Int? = 5000,
        @Query("page") page: Int? = 1,
        @Query("size") size: Int? = 15,
        @Query("sort") sort: String? = Constants.SORT_ACCURACY
    ): LocationResponse
}