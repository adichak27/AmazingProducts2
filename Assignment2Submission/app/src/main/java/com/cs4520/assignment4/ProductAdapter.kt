package com.cs4520.assignment4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cs4520.assignment4.databinding.ItemProductBinding

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private var products : List<Product> = listOf()

    class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.apply {
            val product = products[position]

            if (product.type == "Food") {
                // set background color to light_yellow from the colors.xml
                itemProduct.setBackgroundResource(R.color.light_yellow)
                imageViewProduct.setImageResource(R.drawable.food)
            } else {
                // set background color to light_red from the colors.xml
                itemProduct.setBackgroundResource(R.color.light_red)
                imageViewProduct.setImageResource(R.drawable.equipment)
            }
            textViewName.text = product.name
            textViewPrice.text = "$${product.price}"
            // set or hide the expirary date
            if (product.expiryDate.isNullOrEmpty()) {
                textViewExpiryDate.visibility = View.GONE
            } else {
                textViewExpiryDate.visibility = View.VISIBLE
                textViewExpiryDate.text = product.expiryDate
            }
            imageViewProduct.post {
                val width = imageViewProduct.width
                imageViewProduct.layoutParams.height = width
            }
        }
        /* with(holder.binding) {
            val product = products[position]
            textViewName.text = product.name
            textViewPrice.text = "$${product.price}"
            if (product.expiryDate.isNullOrEmpty()) {
                textViewExpiryDate.visibility = View.GONE
            } else {
                textViewExpiryDate.visibility = View.VISIBLE
                textViewExpiryDate.text = product.expiryDate
            }
            if (product.type == "Food") {
                itemProduct.setBackgroundResource(R.color.light_yellow)
                imageViewProduct.setImageResource(R.drawable.food)
            } else {
                itemProduct.setBackgroundResource(R.color.light_red)
                imageViewProduct.setImageResource(R.drawable.equipment)
            }
            imageViewProduct.post {
                val width = imageViewProduct.width
                imageViewProduct.layoutParams.height = width
            }
        } */
    }
    override fun getItemCount(): Int = products.size

    fun submitList(newProducts: List<Product>) {
        this.products = newProducts
        notifyDataSetChanged()
    }
}