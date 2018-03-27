package cz.levinzonr.cryptostore.model

import android.os.Handler
import android.util.Log
import android.util.TimeUtils
import rx.Observable
import java.util.concurrent.TimeUnit

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

     fun geExchangeRates() : Observable<ArrayList<Currency>> {
        Log.d(TAG, "Start loading data...")
        return Observable.just(items()).delay(2,TimeUnit.SECONDS)
    }

    fun saveRates(list: ArrayList<Currency>) {

    }

}