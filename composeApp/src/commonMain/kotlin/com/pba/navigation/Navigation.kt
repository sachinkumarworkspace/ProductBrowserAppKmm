package com.pba.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.pba.navigation.routes.RouteProductCatalog
import com.pba.product.catalog.domain.model.Product
import com.pba.product.catalog.ui.screen.ProductDetailsScreen
import com.pba.product.catalog.ui.screen.ProductListScreen

@Composable
fun NavigationApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RouteProductCatalog.ProductList
    ) {
        composable<RouteProductCatalog.ProductList> {
            ProductListScreen(
                onProductClick = { productId: Int ->
                    navController.navigate(RouteProductCatalog.ProductDetails(productId = productId)) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                }
            )
        }
        composable<RouteProductCatalog.ProductDetails> { backStackEntry ->
            val args = backStackEntry.toRoute<RouteProductCatalog.ProductDetails>()
            ProductDetailsScreen(
                productId = args.productId,
                onBackClick = navController::popBackStack
            )
        }
    }
}