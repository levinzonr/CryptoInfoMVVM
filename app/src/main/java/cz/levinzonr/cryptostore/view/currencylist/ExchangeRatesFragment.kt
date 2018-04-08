package cz.levinzonr.cryptostore.view.currencylist


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.graphics.Rect
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import cz.levinzonr.cryptostore.R
import cz.levinzonr.cryptostore.databinding.FragmentExchangeRatesBinding
import cz.levinzonr.cryptostore.viewmodel.RatesViewModel


class ExchangeRatesFragment : Fragment() {

    lateinit var binding: FragmentExchangeRatesBinding
    lateinit var rvAdapter: CurrencyItemsAdapter

    companion object {
        const val TAG = "RatesFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exchange_rates, container, false)
        val viewModel = ViewModelProviders.of(this).get(RatesViewModel::class.java)

        binding.ratesViewModel = viewModel
        binding.executePendingBindings()
        initRecyclerView()
        viewModel.items.observe(this, Observer {
            Log.d(TAG, it.toString())
            rvAdapter.addItems(it!!)
        })
        viewModel.getExchangeRates()
        return binding.root
    }

    private fun initRecyclerView() {
        rvAdapter = CurrencyItemsAdapter(context)
        binding.recyclerView.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
                    super.getItemOffsets(outRect, view, parent, state)
                    if (parent  != null && parent.adapter != null) {
                        if (parent.getChildAdapterPosition(view) != parent.adapter.itemCount - 1) {
                            outRect?.bottom = 5
                        }
                    }

                }
            })
        }
    }

}
