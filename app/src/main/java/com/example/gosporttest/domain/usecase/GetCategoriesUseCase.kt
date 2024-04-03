package com.example.gosporttest.domain.usecase

import com.example.gosporttest.data.FoodRepository
import com.example.gosporttest.domain.dto.Category
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) {
    suspend fun execute(): List<Category> {
        return foodRepository.getCategories()
    }
}