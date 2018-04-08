package cz.levinzonr.cryptostore.view.currencydetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import cz.levinzonr.cryptostore.R
import cz.levinzonr.cryptostore.model.Currency

import kotlinx.android.synthetic.main.activity_coin_detail.*

class CoinDetailActivity : AppCompatActivity() {

    companion object {

        const val ARG_COIN = "CoinId"

        fun startAsIntent(context: Context, coin: Currency) {
            Log.d("NULL", coin.toString())
            val startIntent = Intent(context, CoinDetailActivity::class.java)
            startIntent.putExtra(ARG_COIN, coin)
            context.startActivity(startIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        setSupportActionBar(toolbar)

        val coin: Currency = intent.extras.getParcelable(ARG_COIN)
        supportActionBar?.title = coin.name

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, CoinDetailFragment.newInstance(coin.id))
                .commit()
    }

}
