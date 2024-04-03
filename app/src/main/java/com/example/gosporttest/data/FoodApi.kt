package com.example.gosporttest.data

import com.example.gosporttest.data.responsemodel.ResponseCategoryList
import com.example.gosporttest.data.responsemodel.ResponseProductList
import retrofit2.http.GET

interface FoodApi {

    @GET("search.php?s")
    suspend fun getProducts(): ResponseProductList

    @GET("categories.php")
    suspend fun getCategories(): ResponseCategoryList

}