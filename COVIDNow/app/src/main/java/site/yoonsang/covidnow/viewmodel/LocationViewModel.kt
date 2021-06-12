package site.yoonsang.covidnow.viewmodel

import androidx.lifecycle.*
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import site.yoonsang.covidnow.repository.LocationRepository
import site.yoonsang.covidnow.util.DoubleTrigger
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val repository: LocationRepository
) : ViewModel() {

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String>
        get() = _toastMessage

    private val x = MutableLiveData<String>()
    private val y = MutableLiveData<String>()

    init {
        x.value = ""
        y.value = ""
    }

    val document = Transformations.switchMap(DoubleTrigger(x, y)) {
        repository.getLocationResponse(it.first!!, it.second!!).cachedIn(viewModelScope)
    }

    fun getLocationResponse(lon: String, lat: String) {
        x.postValue(lon)
        y.postValue(lat)
    }
}