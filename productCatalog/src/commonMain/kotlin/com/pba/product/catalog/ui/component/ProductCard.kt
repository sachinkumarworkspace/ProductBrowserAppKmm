package com.pba.product.catalog.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.pba.product.catalog.domain.model.Product

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PrevProductCard() {
    ProductCard(
        product = Product(
            id = 1,
            name = "Product 1",
            price = 10.0,
            thumbnail = "data:image/jpeg;base64,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",
            description = "Description 1",
            rating = 4.5,
            category = "Category 1"
        ),
        onProductClick = {}
    )
}

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    product: Product,
    onProductClick: (Product) -> Unit
) {
    Card(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { onProductClick(product) },
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(11.dp)
        ) {
            AsyncImage(
                model = product.thumbnail,
                contentDescription = product.name,
                modifier = Modifier.size(80.dp).clip(RoundedCornerShape(11.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(25.dp))
            Column(
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterVertically),
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "${product.price}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}