package site.yoonsang.covidnow.network

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import site.yoonsang.covidnow.model.CovidInfo
import site.yoonsang.covidnow.model.RegionCovidInfo

interface CovidApi {

    companion object {
        const val BASE_URL = "https://api.corona-19.kr/"
    }

    @GET("/korea")
    fun getCovidInfo(
        @Query("serviceKey") serviceKey: String
    ): Observable<CovidInfo>

    @GET("/korea/country/new")
    fun getRegionCovidInfo(
        @Query("serviceKey") serviceKey: String
    ): Observable<RegionCovidInfo>
}