package cz.levinzonr.cryptostore.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cz.levinzonr.cryptostore.R
import cz.levinzonr.cryptostore.databinding.ItemCurrencyBinding
import cz.levinzonr.cryptostore.model.Currency

class CurrencyItemsAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val items = ArrayList<Currency>()

    inner class ViewHolder(val binding: ItemCurrencyBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: Currency) {
            binding.currency = currency
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
        items.addAll(list)
        notifyDataSetChanged()
    }

}