package cz.levinzonr.cryptostore.view

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cz.levinzonr.cryptostore.R
import cz.levinzonr.cryptostore.databinding.ItemCurrencyBinding
import cz.levinzonr.cryptostore.model.Currency
import java.util.*

class CurrencyItemsAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = ArrayList<Currency>()

    companion object {
         val COLORS = listOf(
                 "#66BB6A", "#D4E157", "#FFA726", "#7E57C2", "#EF5350"
         )
    }

    inner class ViewHolder(val binding: ItemCurrencyBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: Currency) {
            binding.currency = currency
            val index = Math.abs(Random().nextInt() % 5 )
            val color = Color.parseColor(COLORS[index])
            binding.currencyShort.background.setColorFilter(color, PorterDuff.Mode.SRC_IN)
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