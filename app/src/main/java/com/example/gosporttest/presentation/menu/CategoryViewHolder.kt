package com.example.gosporttest.presentation.menu

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.gosporttest.R
import com.example.gosporttest.databinding.CategoryItemBinding
import com.example.gosporttest.domain.dto.Category

data class CategoryItem(
    val category: Category,
    val itemClickListener: (String, Boolean) -> Unit,
    var isFocused: Boolean
)

class CategoryViewHolder private constructor(private val binding: CategoryItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): CategoryViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = CategoryItemBinding.inflate(layoutInflater, parent, false)
            return CategoryViewHolder(binding)
        }
    }

    fun bind(
        item: CategoryItem,
        setFocus: (CategoryItem) -> Unit
    ) {
        binding.root.setOnClickListener {
            if (item.isFocused) item.itemClickListener(item.category.name, true)
            else item.itemClickListener(item.category.name, false)
            setFocus(item)
        }
        if (item.isFocused) {
            binding.root.setCardBackgroundColor(
                ContextCompat.getColor(binding.root.context, R.color.secondary)
            )
            binding.categoryName.setTextColor(
                ContextCompat.getColor(binding.root.context, R.color.primary)
            )
            binding.categoryName.typeface = Typeface.DEFAULT_BOLD
        } else {
            binding.root.setCardBackgroundColor(
                ContextCompat.getColor(binding.root.context, R.color.surface)
            )
            binding.categoryName.setTextColor(
                ContextCompat.getColor(binding.root.context, R.color.text_hint)
            )
            binding.categoryName.typeface = Typeface.DEFAULT

        }

        binding.categoryName.text = item.category.name
    }
}