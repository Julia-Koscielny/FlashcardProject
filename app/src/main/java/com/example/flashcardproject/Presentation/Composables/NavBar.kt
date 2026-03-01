package com.example.flashcardproject.Presentation.Composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flashcardproject.R
import com.example.flashcardproject.ui.theme.PurpleGrey80
import com.example.flashcardproject.ui.theme.Purple40

@Composable
fun NavBarScreen(onClick: () -> Unit){

}
@Composable
fun NavBarContent(modifier: Modifier = Modifier, onClick:(Int)-> Unit) {

    val iconList = listOf(
        R.drawable.folder,
        R.drawable.user,
        R.drawable.flower,
        R.drawable.store,
    )

    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Purple40)
        .height(40.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically) {

        iconList.forEachIndexed { index, i ->
            IconButton(
                onClick = {onClick(index)}
            ) {
                Icon(
                    painter = painterResource(i),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp),
                    tint = Color.White
                )
            }
        }

    }


/*
    val pagerState = rememberPagerState { iconList.size }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .background(Purple40)
                .height(120.dp),
            contentPadding = PaddingValues(horizontal = 140.dp)
        )
        { page ->
            Box(
                modifier = Modifier
                    .padding(20.dp)
                    .background(
                        PurpleGrey80, CircleShape
                    )
            ) {
                Image(
                    painter = painterResource(id = iconList[page]), " ",
                    modifier = Modifier
                        .padding(20.dp)
                )
            }
    }*/
}

    @Preview(showBackground = true)
    @Composable
    fun NavBarScreenPreview() {
        Box(modifier = Modifier
            .fillMaxSize()) {

        }

    }
