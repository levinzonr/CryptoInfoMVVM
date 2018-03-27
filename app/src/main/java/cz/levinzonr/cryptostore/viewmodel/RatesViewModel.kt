package cz.levinzonr.cryptostore.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.util.Log
import cz.levinzonr.cryptostore.model.Currency
import cz.levinzonr.cryptostore.model.CurrencyExchangeModel

class RatesViewModel : ViewModel() {
    init{
        Log.d(TAG, "Init")
    }
    var model = CurrencyExchangeModel()
    val isLoading : ObservableField<Boolean> = ObservableField()
    var items = MutableLiveData<ArrayList<Currency>>()

    companion object {
        const val TAG = "RatesViewModel"
    }

    fun getExcahngeRates() {
        Log.d(TAG, "Refreshing")
        isLoading.set(true)
        model.getExchangeRates(object : CurrencyExchangeModel.OnDataLoadedCallback {
            override fun onLoaded(loadedItems: ArrayList<Currency>) {
                isLoading.set(false)
                items.value = loadedItems
                if (items.hasActiveObservers()) {
                    Log.d(TAG, "has objeserve")
                }
                Log.d(TAG, "Loadint finished")
            }
        })
    }

}