package com.example.flashcardproject.Presentation.Composables.User

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flashcardproject.Presentation.User.UserViewModel


@Composable
fun UserInfoScreen(viewModel: UserViewModel, userId: Long) {
    val userPoints by viewModel
        .getUserPoints(userId)
        .collectAsState("")

    Text(text = userPoints)


}

@Composable
fun UserInfoContent(userPoints: String){
    Column(modifier = Modifier.padding(6.dp)) {
        Text(text = userPoints)
    }
}