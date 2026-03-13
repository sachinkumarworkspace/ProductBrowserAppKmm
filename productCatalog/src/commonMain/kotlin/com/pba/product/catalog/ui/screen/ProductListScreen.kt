package com.pba.product.catalog.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pba.product.catalog.ui.component.ProductCard
import com.pba.product.catalog.ui.component.SearchFilterRow
import com.pba.product.catalog.ui.contract.ProductListEffect
import com.pba.product.catalog.ui.contract.ProductListIntent
import com.pba.product.catalog.ui.contract.ProductListState
import com.pba.product.catalog.ui.viewmodel.ProductListViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductListScreen(
    onProductClick: (Int) -> Unit
) {

    val vm = koinViewModel<ProductListViewModel>()
    val state by vm.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        vm.effect.collectLatest { effect ->
            if (effect is ProductListEffect.NavigateToProductDetails) {
                onProductClick(effect.productId)
            }
        }
    }

    Column(Modifier.fillMaxSize()) {

        Text(
            text = "Product Browser",
            modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )

        ContentProductList(
            state = state,
            onIntent = vm::onIntent
        )
    }
}

@Composable
private fun ContentProductList(
    state: ProductListState,
    onIntent: (ProductListIntent) -> Unit
) {

    var query by remember { mutableStateOf("") }

    val categories = listOf("All") + state.products
        .map { it.category }
        .distinct()
        .sorted()

    Column(Modifier.fillMaxSize()) {

        SearchFilterRow(
            query = query,
            isLoading = state.isLoading,
            categories = categories,
            onQueryChange = {
                query = it
                onIntent(ProductListIntent.SearchProducts(it))
            },
            onClearSearch = {
                query = ""
                onIntent(ProductListIntent.SearchProducts(""))
            },
            onCategorySelected = { category ->
                onIntent(
                    ProductListIntent.SelectCategory(
                        if (category == "All") null else category
                    )
                )
            }
        )

        ProductContent(
            state = state,
            onIntent = onIntent
        )
    }
}

@Composable
private fun ProductContent(
    state: ProductListState,
    onIntent: (ProductListIntent) -> Unit
) {

    when {

        state.isLoading -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        state.error != null -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(state.error)
        }

        state.products.isEmpty() -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No products found",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        else -> LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            items(state.products) { product ->

                ProductCard(
                    modifier = Modifier.padding(8.dp),
                    product = product,
                    onProductClick = {
                        onIntent(ProductListIntent.OnProductClick(it))
                    }
                )
            }
        }
    }
}