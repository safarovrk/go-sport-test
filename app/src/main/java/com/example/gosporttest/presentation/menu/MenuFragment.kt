package com.example.gosporttest.presentation.menu

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.gosporttest.R
import com.example.gosporttest.data.responsemodel.ResponseStates
import com.example.gosporttest.databinding.FragmentMenuBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MenuFragment : Fragment() {

    companion object {
        private const val VIEW_FLIPPER_CONTENT = 0
        private const val VIEW_FLIPPER_LOADING = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var productsAdapter: ProductAdapter

    @Inject
    lateinit var categoriesAdapter: CategoryAdapter

    private val viewModel by createViewModelLazy(
        MenuViewModel::class,
        { this.viewModelStore },
        factoryProducer = { viewModelFactory })

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setStateObserver()

        val productsRecyclerView = binding.productsRecyclerView
        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)
            ?.let { dividerItemDecoration.setDrawable(it) }
        productsRecyclerView.addItemDecoration(dividerItemDecoration)
        productsRecyclerView.adapter = productsAdapter

        val categoriesRecyclerView = binding.categoriesRecyclerView
        categoriesRecyclerView.adapter = categoriesAdapter
    }

    private fun setStateObserver() {
        viewModel.menuState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseStates.Success -> {
                    if (categoriesAdapter.itemCount == 0) {
                        val categoriesList = mutableListOf<CategoryItem>()
                        state.data.categories.forEach { category ->
                            categoriesList.add(
                                CategoryItem(
                                    category,
                                    itemClickListener = { categoryItem, isFocusRemoved ->
                                        viewModel.onCategoryClicked(categoryItem, isFocusRemoved)
                                    },
                                    isFocused = false
                                )
                            )
                        }
                        categoriesAdapter.submitList(categoriesList)
                    }

                    val productsList = mutableListOf<ProductItem>()
                    state.data.products.forEach { product ->
                        productsList.add(
                            ProductItem(
                                product,
                                buttonClickListener = {

                                }
                            )
                        )
                    }
                    productsAdapter.submitList(productsList)


                    binding.viewFlipper.displayedChild = VIEW_FLIPPER_CONTENT
                }

                is ResponseStates.Failure -> {
                    binding.errorText.text = state.e.message
                    binding.viewFlipper.displayedChild = VIEW_FLIPPER_ERROR
                }

                is ResponseStates.Loading -> {
                    binding.viewFlipper.displayedChild = VIEW_FLIPPER_LOADING
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}