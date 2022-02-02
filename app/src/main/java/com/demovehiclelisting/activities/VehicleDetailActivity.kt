package com.demovehiclelisting.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.demovehiclelisting.R
import com.demovehiclelisting.model.Vehicle

class VehicleDetailActivity : AppCompatActivity() {
    private var vehicleModel: Vehicle? = null

    private lateinit var imageView: ImageView
    private lateinit var description: TextView
    private lateinit var price: TextView
    private lateinit var mileage: TextView
    private lateinit var location: TextView
    private lateinit var exteriorColor: TextView
    private lateinit var interiorColor: TextView
    private lateinit var driveType: TextView
    private lateinit var transmission: TextView
    private lateinit var bodyStyle: TextView
    private lateinit var engine: TextView
    private lateinit var fuel: TextView
    private lateinit var callDealer: TextView

    companion object {

        private const val VEHICLE: String = "VEHICLE"

        fun getCallingIntent(context: Context, vehicle: Vehicle): Intent {
            val intent = Intent(context, VehicleDetailActivity::class.java)
            intent.putExtra(VEHICLE, vehicle)
            return intent
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        vehicleModel = intent.extras?.getParcelable<Vehicle>(VEHICLE)

        imageView = findViewById(R.id.vehicleImage)
        description = findViewById(R.id.description)
        price = findViewById(R.id.price)
        mileage = findViewById(R.id.mileage)
        location = findViewById(R.id.location)
        exteriorColor = findViewById(R.id.exteriorColor)
        interiorColor = findViewById(R.id.interiorColor)
        driveType = findViewById(R.id.driveType)
        transmission = findViewById(R.id.transmission)
        bodyStyle = findViewById(R.id.bodyStyle)
        engine = findViewById(R.id.engine)
        fuel = findViewById(R.id.fuel)
        callDealer = findViewById(R.id.callDealer)

        vehicleModel?.let {vehicle ->
            Glide.with(this)
                .load(vehicle.imageUrl)
                .error(R.drawable.no_image_placeholder)
                .into(imageView)
            description.text = "${vehicle.year} ${vehicle.make} ${vehicle.model} ${vehicle.trim}"
            price.text = "$${vehicle.currentPrice}"
            mileage.text = vehicle.mileage.toString()
            location.text = vehicle.dealerAddress
            exteriorColor.text = vehicle.exteriorColor
            interiorColor.text = vehicle.interiorColor
            driveType.text = vehicle.drivetype
            transmission.text = vehicle.transmission
            bodyStyle.text = vehicle.bodytype
            engine.text = vehicle.engine
            fuel.text = vehicle.fuel

            callDealer.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",
                    vehicle.dealerPhone, null))
                startActivity(intent)
            }
        }
    }
}
