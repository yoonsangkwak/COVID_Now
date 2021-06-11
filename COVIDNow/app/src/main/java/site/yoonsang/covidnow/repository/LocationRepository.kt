package site.yoonsang.covidnow.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import androidx.paging.rxjava3.cachedIn
import androidx.paging.rxjava3.observable
import io.reactivex.rxjava3.core.Single
import site.yoonsang.covidnow.model.LocationResponse
import site.yoonsang.covidnow.network.KakaoApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationRepository @Inject constructor(
    private val kakaoApi: KakaoApi
) {

    fun getLocationResponse(x: String, y: String) =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                LocationPagingSource(kakaoApi, x, y)
            }
        ).observable
}