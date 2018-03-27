package cz.levinzonr.cryptostore.model

import android.os.Handler

class CurrencyExchangeModel {

    companion object {
         fun items(): ArrayList<Currency>{
             val items = ArrayList<Currency>()
             items.add(Currency("bitcoint", "Bitcoin", "BTC", 202031.1))
             items.add(Currency("ethereum","Ethereum", "ETH", 12.1844))
             return items
         }

    }

    fun getExchangeRates(callback: OnDataLoadedCallback) {
        Handler().postDelayed({callback.onLoaded(items())}, 2000)
    }

    interface OnDataLoadedCallback {
        fun onLoaded(items: ArrayList<Currency>)
    }
}