package cz.levinzonr.cryptostore.model

import android.content.Context
import cz.levinzonr.cryptostore.NetworkManager
import rx.Observable

class RatesRepository(context: Context) {

    val localData = ExchangeRatesLocal(context)
    val netManager: NetworkManager = NetworkManager(context)

     fun geExchangeRates() : Observable<List<Currency>> {

         if (netManager.isConnectedToInternet()){
             return ExchangeRatesRemote.instance().geExchangeRates()
         }
         return localData.geExchangeRates()
     }

}