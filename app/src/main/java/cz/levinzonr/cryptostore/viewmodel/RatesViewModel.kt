package cz.levinzonr.cryptostore.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.util.Log
import cz.levinzonr.cryptostore.model.Currency
import cz.levinzonr.cryptostore.model.ExchangeRatesRemote
import cz.levinzonr.cryptostore.model.RatesRepo

class RatesViewModel : ViewModel() {
    init{
        Log.d(TAG, "Init")
    }
    var model = RatesRepo()
    val isLoading : ObservableField<Boolean> = ObservableField()
    var items = MutableLiveData<ArrayList<Currency>>()

    companion object {
        const val TAG = "RatesViewModel"
    }

    fun getExchangeRates() {
        Log.d(TAG, "Refreshing")
        isLoading.set(true)
        model.geExchangeRates(object : RatesRepo.OnRepoReadyCallbacks {
            override fun onDataReady(list: ArrayList<Currency>) {
                isLoading.set(false)
                items.value = list
                if (items.hasActiveObservers()) {
                    Log.d(TAG, "has objeserve")
                }
                Log.d(TAG, "Loadint finished")
            }
        })
    }

}