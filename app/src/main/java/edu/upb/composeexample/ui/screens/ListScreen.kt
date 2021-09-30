package edu.upb.composeexample.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Product(val title: String, val description: String, val price: Double)

@Composable
fun ListScreen(productList: List<Product>) {
    Column(modifier = Modifier.padding(5.dp)) {
        productList.map {
            ProductItem(it)
        }
    }
}

@Composable
fun ProductItem(it: Product) {
    Text(it.title)
}