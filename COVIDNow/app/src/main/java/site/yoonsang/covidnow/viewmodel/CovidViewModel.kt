package site.yoonsang.covidnow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import site.yoonsang.covidnow.BuildConfig
import site.yoonsang.covidnow.model.CovidInfo
import site.yoonsang.covidnow.model.RegionCovidInfo
import site.yoonsang.covidnow.repository.CovidRepository
import javax.inject.Inject

@HiltViewModel
class CovidViewModel @Inject constructor(
    private val repository: CovidRepository
) : ViewModel() {

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String>
        get() = _toastMessage

    private val _covidInfo = MutableLiveData<CovidInfo>()
    val covidInfo: LiveData<CovidInfo>
        get() = _covidInfo

    private val _regionCovidInfo = MutableLiveData<RegionCovidInfo>()
    val regionCovidInfo: LiveData<RegionCovidInfo>
        get() = _regionCovidInfo

    fun getCovidResponse() {
        repository.getCovidInfo(BuildConfig.COVID_KEY)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                if (it != null) {
                    _covidInfo.postValue(it)
                } else {
                    _toastMessage.postValue("데이터 조회에 실패했습니다.")
                }
            }, {
                _toastMessage.postValue(it.message ?: "통신 오류")
            })
    }

    fun getRegionCovidResponse() {
        repository.getRegionCovidInfo(BuildConfig.COVID_KEY)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                if (it != null) {
                    _regionCovidInfo.postValue(it)
                } else {
                    _toastMessage.postValue("데이터 조회에 실패했습니다.")
                }
            }, {
                _toastMessage.postValue(it.message ?: "통신 오류")
            })
    }
}