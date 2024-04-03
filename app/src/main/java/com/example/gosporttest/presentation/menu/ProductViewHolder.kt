package com.example.gosporttest.presentation.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.gosporttest.databinding.ProductItemBinding
import com.example.gosporttest.domain.dto.Product

data class ProductItem(
    val product: Product,
    val buttonClickListener: (Product) -> Unit
)

class ProductViewHolder private constructor(private val binding: ProductItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): ProductViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ProductItemBinding.inflate(layoutInflater, parent, false)
            return ProductViewHolder(binding)
        }
    }

    fun bind(item: ProductItem) {
        binding.productTitle.text = item.product.name
        binding.productIngredients.text = item.product.ingredients

        binding.buyButton.setOnClickListener { item.buttonClickListener(item.product) }

        Glide.with(binding.productImage)
            .load(item.product.image)
            .transform(
                MultiTransformation(
                    CenterCrop(),
                    RoundedCorners(16)
                )
            )
            .into(binding.productImage)

    }
}