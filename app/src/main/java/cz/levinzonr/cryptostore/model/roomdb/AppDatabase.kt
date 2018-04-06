package cz.levinzonr.cryptostore.model.roomdb

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import cz.levinzonr.cryptostore.model.Currency

@Database(entities = [Currency::class], version = 1)
abstract  class AppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "AppDatabase"
        private  var instance: AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()

            return instance as AppDatabase
        }

    }

    abstract fun currencyDAO() : CurrencyDAO
}
