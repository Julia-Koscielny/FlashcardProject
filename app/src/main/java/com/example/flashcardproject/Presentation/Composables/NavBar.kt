package com.example.flashcardproject.Presentation.Composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flashcardproject.R
import com.example.flashcardproject.ui.theme.PurpleGrey80
import com.example.flashcardproject.ui.theme.Purple40

@Composable
fun NavBarScreen(){
    NavBarContent()
}
@Composable
fun NavBarContent(modifier: Modifier = Modifier) {

    val iconList = listOf(
        R.drawable.favourites,
        R.drawable.flower,
        R.drawable.search,
        R.drawable.store,
    )


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
            NavBarContent()
        }

    }
