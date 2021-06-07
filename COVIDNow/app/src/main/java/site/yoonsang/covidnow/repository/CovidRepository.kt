package site.yoonsang.covidnow.repository

import io.reactivex.rxjava3.core.Single
import site.yoonsang.covidnow.model.CovidInfo
import site.yoonsang.covidnow.model.RegionCovidInfo
import site.yoonsang.covidnow.network.CovidApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CovidRepository @Inject constructor(
    private val covidApi: CovidApi
) {

    fun getCovidInfo(serviceKey: String): Single<CovidInfo> =
        covidApi.getCovidInfo(serviceKey)

    fun getRegionCovidInfo(serviceKey: String): Single<RegionCovidInfo> =
        covidApi.getRegionCovidInfo(serviceKey)
}