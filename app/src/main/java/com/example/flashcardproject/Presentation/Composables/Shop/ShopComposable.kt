package com.example.flashcardproject.Presentation.Composables.Shop

import android.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.flashcardproject.Data.Resources.AppIcons
import com.example.flashcardproject.Presentation.StoreObject.StoreObjectUi
import java.util.Objects
import androidx.compose.foundation.lazy.grid.items
import com.example.flashcardproject.Presentation.User.UserViewModel

@Composable
fun ShopComposable(
    storeObjects: List<StoreObjectUi>,
    onItemClick: (StoreObjectUi) -> Unit,
    userId: Long,
    userViewModel: UserViewModel
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 120.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(storeObjects) { item ->
        ShopObject(
            item = item,
            onClick = {
                userViewModel.purchaseItem(userId, item.price)
                onItemClick(item) }
        )
    }

    }
}

@Composable
fun ShopObject(
    item: StoreObjectUi,
    onClick: () -> Unit
){

    val iconList = listOf(
        com.example.flashcardproject.R.drawable.folder,
        com.example.flashcardproject.R.drawable.flower,
        com.example.flashcardproject.R.drawable.empty,
        com.example.flashcardproject.R.drawable.store,
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable{onClick()}
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Icon(painter = painterResource(id = iconList[1]),
                contentDescription = null,
                modifier = Modifier.size(40.dp))

            Text(
                text = "${item.price}$",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}