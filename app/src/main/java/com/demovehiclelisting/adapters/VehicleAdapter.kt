package com.demovehiclelisting.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demovehiclelisting.R
import com.demovehiclelisting.activities.VehicleDetailActivity
import com.demovehiclelisting.model.Vehicle


class VehicleAdapter(context: Context, val vehicleList: ArrayList<Vehicle>) : RecyclerView.Adapter<VehicleAdapter.ViewHolder>() {
    private val mContext = context
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.vehicle_card, p0, false)
        return ViewHolder(v);
    }
    override fun getItemCount(): Int {
        return vehicleList.size
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val vehicle = vehicleList[position]
        viewHolder.description?.text = "${vehicle.year} ${vehicle.make} ${vehicle.model} ${vehicle.trim}"
        viewHolder.price?.text = "$${vehicle.currentPrice}"
        viewHolder.mileage?.text = vehicle.mileage.toString()
        viewHolder.location?.text = vehicle.dealerAddress

        Glide.with(mContext)
            .load(vehicle.imageUrl)
            .error(R.drawable.no_image_placeholder)
            .into(viewHolder.image)

        vehicle.dealerPhone?.let { phoneNumber ->
            viewHolder.callDealer.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null))
                mContext.startActivity(intent)
            }
        }

        viewHolder.detailView.setOnClickListener {
            mContext.startActivity(VehicleDetailActivity.getCallingIntent(mContext, vehicle))
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.vehicleImage)
        val description = itemView.findViewById<TextView>(R.id.description)
        val price = itemView.findViewById<TextView>(R.id.price)
        val mileage = itemView.findViewById<TextView>(R.id.mileage)
        val location = itemView.findViewById<TextView>(R.id.location)
        val callDealer = itemView.findViewById<TextView>(R.id.callDealer)
        val detailView = itemView.findViewById<LinearLayout>(R.id.detailView)
    }
}