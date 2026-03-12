package com.pba

import com.pba.products.domain.model.Product
import com.pba.products.domain.repository.ProductRepository
import com.pba.products.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetProductsUseCaseTest {
    private lateinit var productRepository: ProductRepository
    private lateinit var getProductsUseCase: GetProductsUseCase

    @Test
    fun `test getProductsUseCase returns products`() = runTest {
        productRepository = DummyProductsRepository()
        getProductsUseCase = GetProductsUseCase(productRepository)

        val products: List<Product> =getProductsUseCase().first()

        assertTrue(products.isNotEmpty())
        assertEquals(10, products.size)
        assertEquals("Product 7", products[6].name)
    }
}