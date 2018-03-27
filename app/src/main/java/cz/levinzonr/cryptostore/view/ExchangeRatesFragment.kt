package cz.levinzonr.cryptostore.view


import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import cz.levinzonr.cryptostore.R
import cz.levinzonr.cryptostore.databinding.FragmentExchangeRatesBinding
import cz.levinzonr.cryptostore.viewmodel.RatesViewModel


class ExchangeRatesFragment : Fragment() {

    lateinit var binding: FragmentExchangeRatesBinding
    lateinit var adapter: CurrencyItemsAdapter

    companion object {
        const val TAG = "RatesFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exchange_rates, container, false)
        binding.ratesViewModel = ViewModelProviders.of(this).get(RatesViewModel::class.java)
        initRecylerView()
        binding.executePendingBindings()
        return binding.root
    }

    private fun initRecylerView() {
        adapter = CurrencyItemsAdapter()
        binding.recyclerView.apply {
            adapter = adapter
            layoutManager = LinearLayoutManager(context)
        }
    }

}
