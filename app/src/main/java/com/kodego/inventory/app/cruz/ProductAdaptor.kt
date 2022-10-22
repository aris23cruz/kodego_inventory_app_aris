package com.kodego.inventory.app.cruz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kodego.inventory.app.cruz.databinding.RowItemBinding

class ProductAdapter(val products:MutableList<Products>):RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    var onItemClick : ((Products) -> Unit)? = null
    var onUpdateButtonClick : ((Products,Int) -> Unit)? = null
    var onDeleteButtonClick : ((Products,Int) -> Unit)? = null
    inner class ProductViewHolder(val binding: RowItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowItemBinding.inflate(layoutInflater,parent,false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.apply {
            imgProduct.setImageResource(products[position].itemImage)
            itemName.text = products[position].itemName
            tvDescription.text = products[position].itemDescription
            tvQuantity.text = products[position].quantity.toString()
            btnUpdate.setOnClickListener(){
                onUpdateButtonClick?.invoke(products[position],position)
            }
            btnDelete.setOnClickListener(){
                onDeleteButtonClick?.invoke(products[position],position)
            }
        }
        holder.itemView.setOnClickListener() {
           onItemClick?.invoke(products[position])
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }
}