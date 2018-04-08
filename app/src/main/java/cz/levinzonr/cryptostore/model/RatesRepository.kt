package cz.levinzonr.cryptostore.model

import android.content.Context
import android.util.Log
import cz.levinzonr.cryptostore.NetworkManager
import io.reactivex.Flowable

class RatesRepository(context: Context) {

    private val localData = ExchangeRatesLocal(context)
    private val netManager: NetworkManager = NetworkManager(context)
    private var toUpdate = false

     fun geExchangeRates() : Flowable<List<Currency>> {

         if (netManager.isConnectedToInternet()){
             toUpdate = true
             return ExchangeRatesRemote.instance().geExchangeRates().flatMap {
                 return@flatMap localData.saveRates(it).toSingleDefault(it).toFlowable()
             }
         }
         toUpdate = false
         return localData.geExchangeRates()
     }

}