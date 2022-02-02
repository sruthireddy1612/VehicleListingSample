package com.demovehiclelisting.activities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.demovehiclelisting.R
import com.demovehiclelisting.adapters.VehicleAdapter
import com.demovehiclelisting.viewModels.FetchVehicleViewModel


class VehicleListFragment : Fragment() {
    private lateinit var viewModel: FetchVehicleViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FetchVehicleViewModel::class.java)
        viewModel.init()
        viewModel.getVehiclesResponseLiveData()?.observe(this,
            Observer { vehicleFeedResponse ->
                vehicleFeedResponse?.let {
                    recyclerView.adapter =
                        VehicleAdapter(
                            requireContext(),
                            it.mapToVehicleList()
                        )
                } ?: run {
                    Toast.makeText(requireContext(), "Error fetching vehicles", Toast.LENGTH_LONG).show()
                }
            })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.vehicle_list_layout, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        recyclerView.layoutManager = layoutManager
        viewModel.fetchVehicles()
        return view
    }
}