package cz.levinzonr.cryptostore.model

import android.content.Context
import cz.levinzonr.cryptostore.NetworkManager
import rx.Observable

class RatesRepo(context: Context) {

    val localData = ExchangeRatesLocal()
    val netManager: NetworkManager = NetworkManager(context)

     fun geExchangeRates() : Observable<ArrayList<Currency>> {

         if (netManager.isConnectedToInternet()){
             return ExchangeRatesRemote.instance().geExchangeRates()
         }
         return localData.geExchangeRates()
     }

}