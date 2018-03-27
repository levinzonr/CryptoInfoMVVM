package cz.levinzonr.cryptostore.model

import android.arch.lifecycle.ViewModel
import android.os.Handler
import android.util.Log
import android.view.View

class CurrencyExchangeModel{

    companion object {

        const val TAG = "Model"

         fun items(): ArrayList<Currency>{
             val items = ArrayList<Currency>()
             items.add(Currency("bitcoint", "Bitcoin", "BTC", 202031.1))
             items.add(Currency("ethereum","Ethereum", "ETH", 12.1844))
             return items
         }

    }

    fun getExchangeRates(callback: OnDataLoadedCallback) {
        Log.d(TAG, "Start loading data...")
        Handler().postDelayed({callback.onLoaded(items())}, 2000)
    }

    interface OnDataLoadedCallback {
        fun onLoaded(loadedItems: ArrayList<Currency>)
    }

}