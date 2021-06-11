package site.yoonsang.covidnow.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import site.yoonsang.covidnow.model.Document
import site.yoonsang.covidnow.network.KakaoApi
import site.yoonsang.covidnow.util.Constants
import java.io.IOException

class LocationPagingSource(
    private val kakaoApi: KakaoApi,
    private val x: String,
    private val y: String
): PagingSource<Int, Document>() {

    override fun getRefreshKey(state: PagingState<Int, Document>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Document> {
        val position = params.key ?: Constants.STARTING_PAGE_INDEX
        return try {
            val response = kakaoApi.getLocationResponse(
                x = x,
                y = y,
                page = position
            ).body()
            val documents = response?.documents

            LoadResult.Page(
                data = documents!!,
                prevKey = if (position == Constants.STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (documents.size < Constants.ITEM_MEMBERS_IN_PAGE) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}