package cz.levinzonr.cryptostore.view.currencydetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import cz.levinzonr.cryptostore.R

import kotlinx.android.synthetic.main.activity_coin_detail.*

class CoinDetailActivity : AppCompatActivity() {

    companion object {

        const val ARG_COIN = "CoinId"

        fun startAsIntent(context: Context, coinId: String) {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(ARG_COIN, coinId)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        setSupportActionBar(toolbar)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, CoinDetailFragment.newInstance(intent.getStringExtra(ARG_COIN)))
                .commit()
    }

}
