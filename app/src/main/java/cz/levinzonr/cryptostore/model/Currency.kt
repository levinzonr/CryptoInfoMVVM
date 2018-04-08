package cz.levinzonr.cryptostore.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Entity
data class Currency(
        @PrimaryKey
        val id: String,
        val name: String,
        val symbol: String,
        val priceUsd: Double,
        val rank: Int,
        @SerializedName("percent_change_1h")val percentChange1h: Double,
        @SerializedName("percent_change_24h")val percentChange24h: Double,
        @SerializedName("percent_change_7d")val percentChange7d: Double,
        val lastUpdated: Long

) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readDouble(),
                parcel.readInt(),
                parcel.readDouble(),
                parcel.readDouble(),
                parcel.readDouble(),
                parcel.readLong()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(id)
                parcel.writeString(name)
                parcel.writeString(symbol)
                parcel.writeDouble(priceUsd)
                parcel.writeInt(rank)
                parcel.writeDouble(percentChange1h)
                parcel.writeDouble(percentChange24h)
                parcel.writeDouble(percentChange7d)
                parcel.writeLong(lastUpdated)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Currency> {
                override fun createFromParcel(parcel: Parcel): Currency {
                        return Currency(parcel)
                }

                override fun newArray(size: Int): Array<Currency?> {
                        return arrayOfNulls(size)
                }
        }
}