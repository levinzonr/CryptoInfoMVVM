package cz.levinzonr.cryptostore.viewmodel

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
    val items: ObservableField<ArrayList<Currency>> = ObservableField()

    companion object {
        const val TAG = "RatesViewModel"
    }

    fun refresh() {
        Log.d(TAG, "Refreshing")
        isLoading.set(true)
        model.getExchangeRates(object : CurrencyExchangeModel.OnDataLoadedCallback {
            override fun onLoaded(loadedItems: ArrayList<Currency>) {
                isLoading.set(false)
                items.set(loadedItems)
                Log.d(TAG, "Loadint finished")
            }
        })
    }

}