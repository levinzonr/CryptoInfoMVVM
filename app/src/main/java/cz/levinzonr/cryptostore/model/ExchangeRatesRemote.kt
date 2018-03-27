package cz.levinzonr.cryptostore.model

import android.os.Handler
import android.util.Log

class ExchangeRatesRemote {

    companion object {

        const val TAG = "RatesRemote"

         fun items(): ArrayList<Currency>{
             val items = ArrayList<Currency>()
             items.add(Currency("bitcoint", "Bitcoin", "BTC", 202031.1))
             items.add(Currency("ethereum","Ethereum", "ETH", 12.1844))
             return items
         }

    }

     fun geExchangeRates(callbacks: OnRemoteDataReady) {
        Log.d(TAG, "Start loading data...")
        Handler().postDelayed({callbacks.onDataReady(items())}, 2000)
    }

    interface OnRemoteDataReady {
        fun onDataReady(list: ArrayList<Currency>)
    }

}