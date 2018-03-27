package cz.levinzonr.cryptostore.model

class RatesRepo {

    val localData = ExchangeRatesLocal()
    val remoteData = ExchangeRatesRemote()

     fun geExchangeRates(callbacks: OnRepoReadyCallbacks) {
        remoteData.geExchangeRates(object : ExchangeRatesRemote.OnRemoteDataReady{
            override fun onDataReady(list: ArrayList<Currency>) {
                callbacks.onDataReady(list)
                localData.saveRates(list)
            }
        })
    }

    interface OnRepoReadyCallbacks {
        fun  onDataReady(list: ArrayList<Currency>)
    }
}