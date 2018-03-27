package cz.levinzonr.cryptostore.model

import android.os.Handler
import android.util.Log

class ExchangeRatesLocal {

    companion object {

        const val TAG = "RatesLocal"

         fun items(): ArrayList<Currency>{
             val items = ArrayList<Currency>()
             items.add(Currency("bitcoint", "Bitcoin", "BTC", 202031.1))
             items.add(Currency("ethereum","Ethereum", "ETH", 12.1844))
             return items
         }

    }

     fun geExchangeRates(callbacks: OnLocalDataReady) {
        Log.d(TAG, "Start loading data...")
        Handler().postDelayed({callbacks.onDataReady(items())}, 2000)
    }

    fun saveRates(list: ArrayList<Currency>) {

    }

    interface OnLocalDataReady {
        fun onDataReady(list: ArrayList<Currency>)
    }


}