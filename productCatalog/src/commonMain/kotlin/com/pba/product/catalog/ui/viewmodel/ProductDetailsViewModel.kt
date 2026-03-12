package com.pba.product.catalog.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pba.product.catalog.domain.usecase.GetSingleProductUseCase
import com.pba.product.catalog.ui.contract.ProductDetailsEffect
import com.pba.product.catalog.ui.contract.ProductDetailsIntent
import com.pba.product.catalog.ui.contract.ProductDetailsState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val useCase: GetSingleProductUseCase
) : ViewModel() {
    private val productId = MutableStateFlow<Int?>(null)

    private val _effect = MutableSharedFlow<ProductDetailsEffect>()
    val effect = _effect.asSharedFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<ProductDetailsState> = productId
        .flatMapLatest { pId ->
            if (pId == null) flowOf(ProductDetailsState(isLoading = true))
            else {
                useCase(pId).map { product ->
                    ProductDetailsState(
                        isLoading = false,
                        product = product
                    )
                }
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            ProductDetailsState(isLoading = true)
        )

    fun onIntent(intent: ProductDetailsIntent) {
        when (intent) {
            is ProductDetailsIntent.LoadProduct -> productId.value = intent.productId
            is ProductDetailsIntent.OnNavigateBack -> navigateBack()
        }
    }

    fun navigateBack() = viewModelScope.launch {
        _effect.emit(ProductDetailsEffect.NavigateBack)
    }
}