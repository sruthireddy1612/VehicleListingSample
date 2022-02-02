package com.demovehiclelisting.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Vehicle (val year: String?,
                         val make: String?,
                         val model: String?,
                         val trim: String?,
                         var mileage: Long?,
                         var currentPrice: Long?,
                         var exteriorColor: String?,
                         var interiorColor: String?,
                         var engine: String?,
                         var fuel: String?,
                         var drivetype: String?,
                         var transmission: String?,
                         var bodytype: String?,
                         val dealerPhone: String?,
                         val dealerAddress: String?,
                         val imageUrl: String?
): Parcelable