package com.demovehiclelisting.services

import com.demovehiclelisting.model.VehicleFeedResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET


interface FetchVehicleService {
    @GET("/assignment.json")
    fun fetchVehicles(): Observable<VehicleFeedResponse>
}