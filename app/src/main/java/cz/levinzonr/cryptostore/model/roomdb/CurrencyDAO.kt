package cz.levinzonr.cryptostore.model.roomdb

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import cz.levinzonr.cryptostore.model.Currency
import io.reactivex.Flowable

@Dao
interface CurrencyDAO {

    @Query("SELECT * from currency")
    fun getAll() : Flowable<List<Currency>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(items: List<Currency>)
}