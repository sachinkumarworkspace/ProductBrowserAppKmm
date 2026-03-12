package com.pba.product.catalog.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pba.product.catalog.domain.model.Product
import com.pba.product.catalog.domain.usecase.GetProductsUseCase
import com.pba.product.catalog.domain.usecase.SearchProductsUseCase
import com.pba.product.catalog.ui.contract.ProductListEffect
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import com.pba.product.catalog.ui.contract.ProductListIntent
import com.pba.product.catalog.ui.contract.ProductListState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class ProductListViewModel(
    private val getProductsUseCase: GetProductsUseCase,
    private val searchProductsUseCase: SearchProductsUseCase
) : ViewModel() {

    private val searchQuery = MutableStateFlow("")
    private val selectedCategory = MutableStateFlow<String?>(null)

    private val _effect = MutableSharedFlow<ProductListEffect>()
    val effect = _effect.asSharedFlow()

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val state: StateFlow<ProductListState> =
        searchQuery
            .debounce(500)
            .combine(selectedCategory) { query, category ->
                Pair(query, category)
            }
            .flatMapLatest { (query, category) ->
                val productFlow =
                    if (query.isBlank()) getProductsUseCase()
                    else searchProductsUseCase(query)

                productFlow.map { products ->
                    val filteredProducts = if (category.isNullOrBlank()) products
                    else products.filter { it.category == category }

                    ProductListState(
                        isLoading = false,
                        products = filteredProducts
                    )
                }
            }.stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                ProductListState(isLoading = true)
            )


    fun onIntent(intent: ProductListIntent) {
        when (intent) {
            is ProductListIntent.SearchProducts -> searchQuery.value = intent.keyword
            is ProductListIntent.OnProductClick -> navigate(intent.product)
            is ProductListIntent.SelectCategory -> selectedCategory.value = intent.category
        }
    }

    fun navigate(product: Product) = viewModelScope.launch {
        _effect.emit(
            ProductListEffect.NavigateToProductDetails(product.id)
        )
    }

}