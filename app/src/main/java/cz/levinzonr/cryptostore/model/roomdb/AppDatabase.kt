package cz.levinzonr.cryptostore.model.roomdb

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import cz.levinzonr.cryptostore.model.Currency

@Database(entities = [Currency::class], version = 1)
abstract  class AppDatabase : RoomDatabase() {

    abstract fun currencyDAO() : CurrencyDAO
}
