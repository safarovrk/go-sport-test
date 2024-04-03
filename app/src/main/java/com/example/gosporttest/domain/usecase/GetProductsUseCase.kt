package com.example.gosporttest.domain.usecase

import com.example.gosporttest.data.FoodRepository
import com.example.gosporttest.domain.dto.Product
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) {
    suspend fun execute(): List<Product> {
        return foodRepository.getProducts()
    }
}