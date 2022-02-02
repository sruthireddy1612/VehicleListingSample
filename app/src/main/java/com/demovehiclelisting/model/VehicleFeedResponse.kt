package com.demovehiclelisting.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VehicleFeedResponse {

    @SerializedName("listings")
    @Expose
    val vehicleFeedList: ArrayList<VehicleModel>? = null

    fun mapToVehicleList(): ArrayList<Vehicle> {
        val vehicleList = arrayListOf<Vehicle>()
        if (vehicleFeedList != null) {
            for (vehicleModel in vehicleFeedList) {
                vehicleList.add(
                    Vehicle(
                    vehicleModel.year,
                    vehicleModel.make,
                    vehicleModel.model,
                    vehicleModel.trim,
                    vehicleModel.mileage,
                    vehicleModel.currentPrice,
                    vehicleModel.exteriorColor,
                    vehicleModel.interiorColor,
                    vehicleModel.engine,
                    vehicleModel.fuel,
                    vehicleModel.drivetype,
                    vehicleModel.transmission,
                    vehicleModel.bodytype,
                    vehicleModel.dealer?.phone,
                    vehicleModel.dealer?.address,
                    vehicleModel.images?.firstPhoto?.large
                )
                )
            }
        }
        return vehicleList
    }
}

class VehicleModel {
    @SerializedName("year")
    @Expose
    val year: String? = null

    @SerializedName("make")
    @Expose
    val make: String? = null

    @SerializedName("model")
    @Expose
    val model: String? = null

    @SerializedName("trim")
    @Expose
    val trim: String? = null

    @SerializedName("vin")
    @Expose
    val vin: String? = null

    @SerializedName("mileage")
    @Expose
    var mileage: Long? = 0L

    @SerializedName("currentPrice")
    @Expose
    var currentPrice: Long? = 0L

    @SerializedName("exteriorColor")
    @Expose
    var exteriorColor: String? = null

    @SerializedName("interiorColor")
    @Expose
    var interiorColor: String? = null

    @SerializedName("engine")
    @Expose
    var engine: String? = null

    @SerializedName("fuel")
    @Expose
    var fuel: String? = null

    @SerializedName("drivetype")
    @Expose
    var drivetype: String? = null

    @SerializedName("transmission")
    @Expose
    var transmission: String? = null

    @SerializedName("bodytype")
    @Expose
    var bodytype: String? = null

    @SerializedName("dealer")
    @Expose
    val dealer: Dealer? = null

    @SerializedName("images")
    @Expose
    val images: VehicleImages? = null
}

class VehicleImages {
    @SerializedName("firstPhoto")
    @Expose
    val firstPhoto: FirstPhoto? = null
}

class FirstPhoto {
    @SerializedName("large")
    @Expose
    val large: String? = null
}

class Dealer {
    @SerializedName("phone")
    @Expose
    val phone: String? = null

    @SerializedName("address")
    @Expose
    val address: String? = null
}
