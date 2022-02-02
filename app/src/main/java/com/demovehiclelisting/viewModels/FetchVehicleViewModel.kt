package com.demovehiclelisting.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.demovehiclelisting.model.VehicleFeedResponse
import com.demovehiclelisting.repository.FetchVehicleRepository


class FetchVehicleViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var carouselMovieRepository: FetchVehicleRepository
    private lateinit var vehicleResponseLiveData: LiveData<VehicleFeedResponse>

    fun init() {
        carouselMovieRepository =
            FetchVehicleRepository()
        vehicleResponseLiveData = carouselMovieRepository.getVehicleResponseLiveData()
    }

    fun fetchVehicles() {
        carouselMovieRepository.fetchVehicles()
    }

    fun getVehiclesResponseLiveData(): LiveData<VehicleFeedResponse>? {
        return vehicleResponseLiveData
    }
}