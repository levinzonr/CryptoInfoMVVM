package cz.levinzonr.cryptostore.model

import com.google.gson.annotations.SerializedName

class Currency(
        val id: String,
        val name: String,
        val symbol: String,
        val priceUsd: Double,
        val rank: Int,
        @SerializedName("percent_change_1h")val percentChange1h: Double,
        @SerializedName("percent_change_24h")val percentChange24h: Double,
        @SerializedName("percent_change_7d")val percentChange7d: Double,
        val lastUpdated: Long

)