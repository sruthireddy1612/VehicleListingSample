package com.demovehiclelisting.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demovehiclelisting.model.VehicleFeedResponse
import com.demovehiclelisting.services.FetchVehicleService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class FetchVehicleRepository {
    private val fetchVehicleService: FetchVehicleService
    private val vehicleResponseLiveData = MutableLiveData<VehicleFeedResponse>()

    fun fetchVehicles() {

        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            fetchVehicleService.fetchVehicles()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({response -> onResponse(response)}, {t -> onFailure(t) }))
    }

    private fun onResponse(response: VehicleFeedResponse) {
        if (response != null) {
            vehicleResponseLiveData.postValue(response)
        }
    }

    private fun onFailure(t: Throwable) {
        Log.e("Failure getting list",t.message)
        vehicleResponseLiveData.postValue(null)
    }

    fun getVehicleResponseLiveData(): LiveData<VehicleFeedResponse> {
        return vehicleResponseLiveData
    }

    companion object {
        private const val VEHICLE_FETCH_SERVICE_BASE_URL = "https://carfax-for-consumers.firebaseio.com"
    }

    init {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()

        fetchVehicleService = Retrofit.Builder()
            .baseUrl(VEHICLE_FETCH_SERVICE_BASE_URL)
            .client(httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FetchVehicleService::class.java)
    }
}