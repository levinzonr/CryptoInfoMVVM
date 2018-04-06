package cz.levinzonr.cryptostore.model

import android.util.Log
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class ExchangeRatesRemote {

    private val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    private val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    private val service = retrofit.create(CoinMarketCapService::class.java)

    companion object {
        const val BASE_URL = "https://api.coinmarketcap.com/v1/"
        const val TAG = "RatesRemote"

        var instance : ExchangeRatesRemote? = null

        fun instance() : ExchangeRatesRemote {
            if (instance == null) {
                instance = ExchangeRatesRemote()
            }
            return instance as ExchangeRatesRemote
        }

    }

     fun geExchangeRates() : Flowable<List<Currency>> {
        Log.d(TAG, "Start loading data...")
         return service.getExchangeRates()
    }

    interface CoinMarketCapService {
        @GET("ticker") fun getExchangeRates() : Flowable<List<Currency>>
    }

}