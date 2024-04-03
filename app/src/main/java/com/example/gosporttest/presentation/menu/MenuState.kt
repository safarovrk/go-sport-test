package com.example.gosporttest.presentation.menu

import com.example.gosporttest.domain.dto.Category
import com.example.gosporttest.domain.dto.Product

class MenuState(
    val categories: List<Category>,
    val products: List<Product>
)