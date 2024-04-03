package com.example.gosporttest.data

import com.example.gosporttest.data.responsemodel.toDomain
import com.example.gosporttest.domain.dto.Category
import com.example.gosporttest.domain.dto.Product
import javax.inject.Inject

class FoodRepository @Inject constructor(
    private val foodApi: FoodApi
) {

    suspend fun getProducts(): List<Product> {
        return foodApi.getProducts().meals.map { it.toDomain() }
    }

    suspend fun getCategories(): List<Category> {
        return foodApi.getCategories().categories.map { it.toDomain() }
    }

}