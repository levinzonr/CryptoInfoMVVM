package cz.levinzonr.cryptostore.model

import android.arch.persistence.room.Room
import android.content.Context
import android.util.Log
import cz.levinzonr.cryptostore.model.roomdb.AppDatabase
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

class ExchangeRatesLocal(val context: Context) {
    private val database: AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()

    companion object {

        const val TAG = "RatesLocal"
        const val DB_NAME = "AppDatabase"
         fun items(): List<Currency>{
             val items = ArrayList<Currency>()
             items.add(Currency("bitcoint",
                     "Bitcoin", "BTC",
                     202031.1, 1, 22.2,
                     2.2, 2.2, System.currentTimeMillis()))
             items.add(Currency("ethereum","Ethereum", "ETH", 202031.1, 1, 22.2,
                     2.2, 2.2, System.currentTimeMillis()))
             return items
         }

    }

     fun geExchangeRates() : Flowable<List<Currency>> {
        Log.d(TAG, "Start loading data...")
        return Flowable.just(items()).delay(2,TimeUnit.SECONDS)
    }

    fun saveRates(list: ArrayList<Currency>) {

    }

}