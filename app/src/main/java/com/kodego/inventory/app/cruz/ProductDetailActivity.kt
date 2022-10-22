package com.kodego.inventory.app.cruz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kodego.inventory.app.cruz.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityProductDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //data from home activity
        var itemName:String? = intent.getStringExtra("itemName")
        var itemDescription:String? = intent.getStringExtra("itemDescription")
        var itemImage: Int = intent.getIntExtra("itemImage",0)

        binding.imgItem2.setImageResource(itemImage)
        binding.txtItemName2.text = itemName
        binding.txtDescription2.text = itemDescription
    }
}