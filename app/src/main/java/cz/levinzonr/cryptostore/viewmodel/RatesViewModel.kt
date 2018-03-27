package cz.levinzonr.cryptostore.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import android.util.Log
import cz.levinzonr.cryptostore.model.Currency
import cz.levinzonr.cryptostore.model.RatesRepo
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class RatesViewModel(app: Application) : AndroidViewModel(app) {
    init{
        Log.d(TAG, "Init")
    }
    var model = RatesRepo(app)
    val isLoading : ObservableField<Boolean> = ObservableField()
    var items = MutableLiveData<ArrayList<Currency>>()

    var subscription: Subscription? = null

    companion object {
        const val TAG = "RatesViewModel"
    }

    fun getExchangeRates() {
        Log.d(TAG, "Refreshing")
        isLoading.set(true)
        subscription  = model.geExchangeRates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ArrayList<Currency>>(){

            override fun onNext(t: ArrayList<Currency>?) {
                Log.d(TAG, "onNext: $t")
                items.value = t
            }

            override fun onCompleted() {
                Log.d(TAG, "onComplete")
                isLoading.set(false)

            }

            override fun onError(e: Throwable?) {
                Log.d(TAG, "Error: $e")
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        subscription?.unsubscribe()
    }

}