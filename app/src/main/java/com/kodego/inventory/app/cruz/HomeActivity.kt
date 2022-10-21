package com.kodego.inventory.app.cruz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodego.inventory.app.cruz.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var productList = mutableListOf<Products>(
            Products(R.drawable.ic_baseline_local_car_wash_24, "Car Model", "This is a car"),
            Products(R.drawable.ic_baseline_local_taxi_24,"Car Model", "This is for transpo"),
            Products(R.drawable.ic_baseline_local_shipping_24,"Truck Model","This is for cargo"),
            Products(R.drawable.ic_baseline_local_car_wash_24, "Car Model", "This is a car"),
            Products(R.drawable.ic_baseline_local_taxi_24,"Car Model", "This is for transpo"),
            Products(R.drawable.ic_baseline_local_shipping_24,"Truck Model","This is for cargo"),
            Products(R.drawable.ic_baseline_local_car_wash_24, "Car Model", "This is a car"),
            Products(R.drawable.ic_baseline_local_taxi_24,"Car Model", "This is for transpo"),
            Products(R.drawable.ic_baseline_local_shipping_24,"Truck Model","This is for cargo"),
            Products(R.drawable.ic_baseline_local_car_wash_24, "Car Model", "This is a car"),
            Products(R.drawable.ic_baseline_local_taxi_24,"Car Model", "This is for transpo"),
            Products(R.drawable.ic_baseline_local_shipping_24,"Truck Model","This is for cargo"),
            Products(R.drawable.ic_baseline_local_car_wash_24, "Car Model", "This is a car"),
            Products(R.drawable.ic_baseline_local_taxi_24,"Car Model", "This is for transpo"),
            Products(R.drawable.ic_baseline_local_shipping_24,"Truck Model","This is for cargo")

        )
        //pass data source to adapter
        val adapter = ProductAdapter(productList)

        binding.myRecycler.adapter = adapter
        binding.myRecycler.layoutManager = LinearLayoutManager(this)

        //get data from another screen
        var name:String? = intent.getStringExtra("nameID")

        binding.tvHomePage.text = "Hello $name!"
    }
}