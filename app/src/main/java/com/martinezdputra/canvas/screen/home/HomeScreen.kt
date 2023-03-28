package com.martinezdputra.canvas.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onItemClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(homeItems) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(item.route)
                    },
                shape = RoundedCornerShape(8.dp),
                elevation = 4.dp,
            ) {
                Text(
                    modifier = Modifier.
                        padding(16.dp),
                    text = item.label
                )
            }
        }
    }
}
