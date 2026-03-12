package com.pba.product.catalog.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CategoryFilter(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {

        Text(
            text = "Category: $selectedCategory",
            modifier = Modifier.align(Alignment.CenterStart)
        )

        IconButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = { expanded = true }
        ) {
            Icon(
                imageVector = Icons.Default.FilterList,
                contentDescription = "Filter"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {

            categories.forEach { category ->

                DropdownMenuItem(
                    text = { Text(category) },
                    onClick = {
                        expanded = false
                        onCategorySelected(category)
                    }
                )
            }
        }
    }
}