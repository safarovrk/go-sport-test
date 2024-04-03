package com.example.gosporttest.data.responsemodel

import com.example.gosporttest.domain.dto.Category
import com.google.gson.annotations.SerializedName

class ResponseCategoryList(
    @SerializedName("categories") val categories: List<ResponseCategory>
)

class ResponseCategory(
    @SerializedName("idCategory") val idCategory: String,
    @SerializedName("strCategory") val strCategory: String,
    @SerializedName("strCategoryThumb") val strCategoryThumb: String,
    @SerializedName("strCategoryDescription") val strCategoryDescription: String
)

fun ResponseCategory.toDomain(): Category {
    return Category(
        id = idCategory,
        name = strCategory
    )
}
