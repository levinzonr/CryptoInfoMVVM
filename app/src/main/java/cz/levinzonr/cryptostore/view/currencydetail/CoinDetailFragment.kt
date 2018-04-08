package cz.levinzonr.cryptostore.view.currencydetail


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import cz.levinzonr.cryptostore.R
import cz.levinzonr.cryptostore.databinding.FragmentCoinDetailBinding
import cz.levinzonr.cryptostore.viewmodel.DetailViewModel

class CoinDetailFragment : Fragment() {

    lateinit var binding: FragmentCoinDetailBinding

    companion object {
        const val ARG_COIN = "ArgCoinId"

        fun newInstance(string: String) : CoinDetailFragment {
            val fragment = CoinDetailFragment()
            val bundle = Bundle()
            bundle.putString(ARG_COIN, string)
            fragment.arguments = bundle
            return  fragment
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_coin_detail, container, false)
        val viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        binding.detailVM = viewModel
        viewModel.getDetail(arguments.getString(ARG_COIN))
        viewModel.currency.observe(this, Observer {
            binding.currency = it!!
            updateImage(binding.trendDayImage, it.percentChange24h >= 0)
            updateImage(binding.trendHourImage, it.percentChange1h >= 0)
            updateImage(binding.trendWeekImage, it.percentChange7d >= 0)
            binding.executePendingBindings()
        })
        binding.executePendingBindings()
        return binding.root
    }

    private fun updateImage(imageView: ImageView, isTrending: Boolean) {
        if (isTrending) {
            imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_trending_up_black_24dp))
            imageView.setColorFilter(Color.GREEN)
        } else {
            imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_trending_down_black_24dp))
            imageView.setColorFilter(Color.RED)
        }
    }
}
