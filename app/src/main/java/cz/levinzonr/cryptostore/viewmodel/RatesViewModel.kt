package cz.levinzonr.cryptostore.viewmodel

import android.databinding.ObservableField
import cz.levinzonr.cryptostore.model.Currency
import cz.levinzonr.cryptostore.model.CurrencyExchangeModel

class RatesViewModel {

    var model = CurrencyExchangeModel()
    val isLoading : ObservableField<Boolean> = ObservableField()
    val items: ObservableField<ArrayList<Currency>> = ObservableField()

    fun refresh() {
        isLoading.set(true)
        model.getExchangeRates(object : CurrencyExchangeModel.OnDataLoadedCallback {
            override fun onLoaded(loadedItems: ArrayList<Currency>) {
                isLoading.set(false)
                items.set(loadedItems)
            }
        })
    }

}