package cz.levinzonr.cryptostore.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import android.util.Log
import cz.levinzonr.cryptostore.model.Currency
import cz.levinzonr.cryptostore.model.RatesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Publisher

class RatesViewModel(app: Application) : AndroidViewModel(app) {
    init{
        Log.d(TAG, "Init")
    }
    var repository = RatesRepository(app)
    val isLoading : ObservableField<Boolean> = ObservableField()
    var items = MutableLiveData<ArrayList<Currency>>()
    var cd = CompositeDisposable()

    companion object {
        const val TAG = "RatesViewModel"
    }

    fun getExchangeRates() {
        Log.d(TAG, "Refreshing")
        isLoading.set(true)
        cd.add(repository.geExchangeRates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { value ->
                            items.value = ArrayList(value)
                            isLoading.set(false)
                        },
                        { e: Throwable? -> isLoading.set(false); Log.d(TAG, "onErrir: $e") },
                        { isLoading.set(false) }
                ))
    }

    override fun onCleared() {
        super.onCleared()
        if (!cd.isDisposed) {
            cd.dispose()
        }
    }
}