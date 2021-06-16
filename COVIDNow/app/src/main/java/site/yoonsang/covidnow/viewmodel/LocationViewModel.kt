package site.yoonsang.covidnow.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import site.yoonsang.covidnow.model.Document
import site.yoonsang.covidnow.repository.LocationRepository
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val repository: LocationRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String>
        get() = _toastMessage

    private val _document = MutableLiveData<PagingData<Document>>()
    val document: LiveData<PagingData<Document>>
        get() = _document

    fun getLocationResponse(x: String, y: String) {
        compositeDisposable.add(
            repository.getLocationResponse(x, y)
                .subscribeOn(Schedulers.io())
                .subscribe({ data ->
                    _document.postValue(data)
                }, { t ->
                    _toastMessage.postValue(t.message ?: "통신 오류")
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}