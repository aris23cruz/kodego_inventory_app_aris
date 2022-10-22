package com.kodego.inventory.app.cruz

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodego.inventory.app.cruz.databinding.ActivityHomeBinding
import com.kodego.inventory.app.cruz.databinding.AddDialogBinding
import com.kodego.inventory.app.cruz.databinding.QuantityDialogBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var adapter : ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //data source
        var productList :MutableList<Products> = mutableListOf<Products>(
            Products(R.drawable.battery, "Battery", "Gold 12V/2SM/D26/N50/(21mos Warranty) Maintenance Free Car/Automotive Battery",0),
            Products(R.drawable.bearings,"Bearings", "Front Wheel Bearings 2 pieces. Includes: Part Number: 91053-K03-N41 BEARING SIZE 6301-2 pieces. Rear Wheel Bearings 2 pieces. Includes: Part Numbers: 91051-K35-V04-BEARING SIZE 6203-1 piece. 91051-KPS-732-BEARING SIZE 6303-1 pieceBundle Set: No. Compatible Motorcycle Models: Honda XR150L. Compatible Motorcycle Manufacturer: Honda. Honda Genuine Parts.",0),
            Products(R.drawable.brakefluid,"Brake Fluid","Brake fluid for motorcycle. DOT 3. High quality. Net content: 150 ml. Superior braking action. Superior safety. Prevents leaks on rubber cup. Prevents rust and corrosion. Prevents moisture build-up. This high-boiling-point brake fluid gives an extra margin of safety. Designed for safe operation of today s modern high-powered vehicles. Excellent for brake and clutch system.",0),
            Products(R.drawable.brakepads, "Brake Pads", "Size:190*157*70; Material: semi material ; Purpose: automotive disc brake lining",0),
            Products(R.drawable.fanbelt,"Fan Belt", "Fan Belt 13x785Li Brand New Japan quality assured replacement part",0),
            Products(R.drawable.headlight,"Headlight","BILUX H4 12V 60/55W \n" +
                    "ORIGINAL SPARE PART\n" +
                    "P43t\n" +
                    "64193\n" +
                    "472 ",0),
            Products(R.drawable.motoroil, "Motor Oil", "Fully Synthetic Oil | 4 Liters/5W-30",0),
            Products(R.drawable.oilfilter,"Oil Filter", "Vic Oil Filter C-415 Mitsubishi Lancer, Galant, Mirage, Outlander Grandis, Montero 4N15, Hyundai Eon, Getz, i10, Kia Picanto Vic",0),
            Products(R.drawable.sprockets,"Sprockets","CSL Motor Parts Japan Quality Sprocket for Supremo 428-15T",0),
            Products(R.drawable.tire,"Tires","Continental ExtremeContact DWS06 Plus Tire | 225/45ZR17 91W",0)
        )
        //pass data source to adapter
        adapter = ProductAdapter(productList)
        adapter.onItemClick = {
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("itemName",it.itemName)
            intent.putExtra("itemDescription",it.itemDescription)
            intent.putExtra("itemImage",it.itemImage)
            intent.putExtra("quantity",it.quantity)
            startActivity(intent)
        }
        adapter.onUpdateButtonClick = {item:Products, position:Int ->
            showUpdateDialog(item,position)
        }
        adapter.onDeleteButtonClick = {item:Products, position:Int ->
            adapter.products.removeAt(position)
            adapter.notifyDataSetChanged()
        }
        binding.flAddBtn.setOnClickListener(){
            showAddDialog()
        }
        binding.myRecycler.adapter = adapter
        binding.myRecycler.layoutManager = LinearLayoutManager(this)

        //get data from another screen
        val name:String? = intent.getStringExtra("nameID")
        binding.tvHomePage.text = "Hello $name!"
    }
    fun showUpdateDialog(item:Products, position:Int){
        val dialog = Dialog(this)
        val binding: QuantityDialogBinding = QuantityDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        dialog.show()

        binding.btnUpdateDialog.setOnClickListener(){
            var newQuantity : Int = binding.etQuantityDialog.text.toString().toInt()
            adapter.products[position].quantity = newQuantity
            adapter.notifyDataSetChanged()
            dialog.dismiss()
        }
    }
    fun showAddDialog(){
        val dialog = Dialog(this)
        val binding: AddDialogBinding = AddDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        dialog.show()

        val images = arrayListOf<String>("battery",
                "bearings","brakefluid",
                "brakepads","fanbelt",
                "headlight","motoroil",
                "oilfilter","sprockets","tire"
        )
        val spinnerAdapter = ArrayAdapter(applicationContext,R.layout.textview_xml,images)
        binding.spnImage.adapter = spinnerAdapter

        binding.btnAdd.setOnClickListener() {
            var image: Int = resources.getIdentifier(binding.spnImage.selectedItem.toString(),"drawable",packageName)
            var itemName: String = binding.etAddDialogName.text.toString()
            var itemDesc : String = binding.etAddDialogDesc.text.toString()
            var quantity : Int = binding.etDialogQuantity.text.toString().toInt()

            adapter.products.add(Products(image, itemName, itemDesc, quantity))
            adapter.notifyItemInserted(adapter.itemCount + 1)
            dialog.dismiss()
        }
    }
}