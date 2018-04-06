package cz.levinzonr.cryptostore.model

import android.arch.persistence.room.Room
import android.content.Context
import android.util.Log
import cz.levinzonr.cryptostore.model.roomdb.AppDatabase
import io.reactivex.Completable
import io.reactivex.Flowable
import java.util.*
import java.util.concurrent.TimeUnit

class ExchangeRatesLocal(val context: Context) {
    private val database: AppDatabase = AppDatabase.getInstance(context)

     fun geExchangeRates() : Flowable<List<Currency>> {
       return database.currencyDAO().getAll()
    }

    fun saveRates(list: List<Currency>) : Completable {
        return  Completable.fromRunnable({ database.currencyDAO().insert(list)})
    }

}