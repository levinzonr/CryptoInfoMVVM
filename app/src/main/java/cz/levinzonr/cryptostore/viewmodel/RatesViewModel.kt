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
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class RatesViewModel(app: Application) : AndroidViewModel(app) {
    init{
        Log.d(TAG, "Init")
    }
    var model = RatesRepository(app)
    val isLoading : ObservableField<Boolean> = ObservableField()
    var items = MutableLiveData<ArrayList<Currency>>()
    var cd = CompositeDisposable()

    companion object {
        const val TAG = "RatesViewModel"
    }

    fun getExchangeRates() {
        Log.d(TAG, "Refreshing")
        isLoading.set(true)
        cd.add(model.geExchangeRates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<Currency>>() {
                    override fun onComplete() {
                        Log.d(TAG, "Oncomplet")
                        isLoading.set(false)
                    }

                    override fun onNext(t: List<Currency>) {
                        Log.d(TAG, "Oncomplet")
                        items.value = ArrayList(t)
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "Oncomplet")
                        isLoading.set(false)
                    }
                }))

    }

    override fun onCleared() {
        super.onCleared()
        if (!cd.isDisposed) {
            cd.dispose()
        }
    }
}