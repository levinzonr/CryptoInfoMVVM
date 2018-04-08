package cz.levinzonr.cryptostore.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import cz.levinzonr.cryptostore.R
import cz.levinzonr.cryptostore.model.Currency
import cz.levinzonr.cryptostore.view.currencydetail.CoinDetailActivity
import cz.levinzonr.cryptostore.view.currencylist.ExchangeRatesFragment

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ExchangeRatesFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onCurrencySelected(currency: Currency) {
        CoinDetailActivity.startAsIntent(this, currency)
    }
}
