package cz.levinzonr.cryptostore

import android.content.Context
import android.net.ConnectivityManager

class NetworkManager(private var applicationContext: Context) {

    fun isConnectedToInternet() : Boolean {
            val conManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = conManager.activeNetworkInfo
            return netInfo != null && netInfo.isConnected
        }

}