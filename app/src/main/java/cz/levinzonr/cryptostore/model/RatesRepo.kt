package cz.levinzonr.cryptostore.model

import android.content.Context
import cz.levinzonr.cryptostore.NetworkManager
import rx.Observable

class RatesRepo(context: Context) {

    val localData = ExchangeRatesLocal()
    val remoteData = ExchangeRatesRemote()
    val netManager: NetworkManager = NetworkManager(context)

     fun geExchangeRates() : Observable<ArrayList<Currency>> {

         if (netManager.isConnectedToInternet()){
             remoteData.geExchangeRates()
         }
         return localData.geExchangeRates()
     }

}