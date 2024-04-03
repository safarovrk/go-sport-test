package com.example.gosporttest.presentation.menu

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class CategoryAdapter @Inject constructor() : RecyclerView.Adapter<CategoryViewHolder>() {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CategoryItem>() {
            override fun areItemsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean =
                oldItem.category == newItem.category

            override fun areContentsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean =
                (oldItem.category == newItem.category && oldItem.isFocused == newItem.isFocused)
        }
    }

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    fun submitList(categories: List<CategoryItem>) {
        differ.submitList(categories)
    }

    private fun setFocus(categoryItem: CategoryItem) {
        val changedFocusList = differ.currentList.map { it.copy() }
        changedFocusList.forEach {
            if (it.isFocused && it == categoryItem) it.isFocused = false
            else if (!it.isFocused && it == categoryItem) it.isFocused = true
            else it.isFocused = false
        }
        submitList(changedFocusList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder.from(parent)
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(differ.currentList[position]) { setFocus(differ.currentList[position]) }
    }
}