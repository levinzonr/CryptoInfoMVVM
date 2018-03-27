package cz.levinzonr.cryptostore.model

import android.os.Handler
import android.util.Log
import rx.Observable
import java.util.concurrent.TimeUnit

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

     fun geExchangeRates() : Observable<ArrayList<Currency>> {
        Log.d(TAG, "Start loading data...")
         return Observable.just(ExchangeRatesLocal.items()).delay(2, TimeUnit.SECONDS)
    }



}