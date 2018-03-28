package cz.levinzonr.cryptostore.view

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cz.levinzonr.cryptostore.R
import cz.levinzonr.cryptostore.databinding.ItemCurrencyBinding
import cz.levinzonr.cryptostore.model.Currency

class CurrencyItemsAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = ArrayList<Currency>()
    inner class ViewHolder(val binding: ItemCurrencyBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: Currency) {
            binding.currency = currency
            binding.currencyTrendingPercentage.text = context.getString(R.string.global_percentage, currency.percentChange1h)
            if (currency.percentChange1h >= 0) {
                binding.currencyTrendingImage.setImageResource(R.drawable.ic_trending_up_black_24dp)
                binding.currencyTrendingImage.setColorFilter(Color.GREEN)
            } else {
                binding.currencyTrendingImage.setImageResource(R.drawable.ic_trending_down_black_24dp)
                binding.currencyTrendingImage.setColorFilter(Color.RED)
            }
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val binding = ItemCurrencyBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as ViewHolder).bind(items[position])
    }

    fun addItems(list: ArrayList<Currency>) {
        Log.d("Adapter", "ITems added")
        items.addAll(list)
        notifyDataSetChanged()
    }

}