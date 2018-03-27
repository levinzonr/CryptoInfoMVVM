package cz.levinzonr.cryptostore.model

import android.os.Handler
import android.util.Log
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import rx.Observable
import java.util.concurrent.TimeUnit

class ExchangeRatesRemote {

    private val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    private val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
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

     fun geExchangeRates() : Observable<ArrayList<Currency>> {
        Log.d(TAG, "Start loading data...")
         return service.getExchangeRates()
    }

    interface CoinMarketCapService {
        @GET("ticker") fun getExchangeRates() : Observable<ArrayList<Currency>>
    }

}