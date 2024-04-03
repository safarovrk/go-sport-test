package com.example.gosporttest.presentation.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gosporttest.data.responsemodel.ResponseStates
import com.example.gosporttest.domain.dto.Category
import com.example.gosporttest.domain.dto.Product
import com.example.gosporttest.domain.usecase.GetCategoriesUseCase
import com.example.gosporttest.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
) : ViewModel() {

    private val _menuState = MutableLiveData<ResponseStates<MenuState>>()
    val menuState: LiveData<ResponseStates<MenuState>> = _menuState

    private var products: List<Product> = listOf()
    private var filteredProducts: List<Product> = listOf()

    private var categories: List<Category> = listOf()

    init {
        onLoadData()
    }

    private fun onLoadData() = viewModelScope.launch {
        _menuState.value = ResponseStates.Loading()
        try {
            categories = getCategoriesUseCase.execute()
            products = getProductsUseCase.execute()

            _menuState.value = ResponseStates.Success(MenuState(categories, products))
        } catch (e: Exception) {
            _menuState.value = ResponseStates.Failure(e)
        }
    }

    fun onCategoryClicked(categoryName: String, isFocusRemoved: Boolean) {
        if (isFocusRemoved) {
            _menuState.value = ResponseStates.Success(MenuState(categories, products))
        } else {
            filteredProducts = products.filter { it.category == categoryName }
            _menuState.value = ResponseStates.Success(MenuState(categories, filteredProducts))
        }
    }
}