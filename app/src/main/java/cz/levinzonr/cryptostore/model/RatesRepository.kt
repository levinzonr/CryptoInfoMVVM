package cz.levinzonr.cryptostore.model

import android.content.Context
import cz.levinzonr.cryptostore.NetworkManager
import io.reactivex.Flowable

class RatesRepository(context: Context) {

    val localData = ExchangeRatesLocal(context)
    val netManager: NetworkManager = NetworkManager(context)

     fun geExchangeRates() : Flowable<List<Currency>> {

         if (netManager.isConnectedToInternet()){
             return ExchangeRatesRemote.instance().geExchangeRates()
         }
         return localData.geExchangeRates()
     }

}