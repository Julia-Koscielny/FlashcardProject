package com.example.flashcardproject.Presentation.Composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashcardproject.Data.Resources.AppIcons
import com.example.flashcardproject.Presentation.Folder.FolderUi
import com.example.flashcardproject.Presentation.Folder.FolderViewModel
import com.example.flashcardproject.R
import com.example.flashcardproject.ui.theme.PurpleGrey80


// FOLDERS INTERFACE
@Composable
fun FoldersInterfaceScreen(
    viewModel: FolderViewModel,
    onAddFolderClick: () -> Unit,
    onDeleteFolderClick: (Long) -> Unit
) {
    val uiState by viewModel.flashCardsAppUiState.collectAsState()

    FoldersInterfaceContent(
            folderList = uiState.folders,
            onAddFolderClick = onAddFolderClick,
            onDeleteFolderClick = onDeleteFolderClick
        )
    }


@Composable
private fun FoldersInterfaceContent(
    folderList: List<FolderUi>,
    onAddFolderClick: () -> Unit,
    onDeleteFolderClick: (Long) -> Unit
){

    Column(modifier = Modifier
        .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        LazyColumn( modifier = Modifier.weight(1f)) {
            items(items = folderList,
                key = { it.id }){
                    folder ->
                FolderMin(
                    folder = folder,
                    onDeleteFolderClick = onDeleteFolderClick
                )
            }
        }

        Button(onClick = onAddFolderClick) {
            Text("Add folder")
        }
    }

}

@Composable
fun FolderMin(folder: FolderUi, onDeleteFolderClick: (Long) -> Unit){

    Row( modifier = Modifier
        .background(PurpleGrey80, RectangleShape)
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .heightIn(max = 60.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(R.drawable.folder), "",
            modifier = Modifier.
        padding(10.dp))
        Text( text = folder.name,
            modifier = Modifier.padding(16.dp))

        Spacer(modifier = Modifier
            .weight(1f)
            )

        Button(modifier = Modifier
            .heightIn(max = 30.dp)
            .widthIn(max = 25.dp),
            onClick = { onDeleteFolderClick(folder.id)}
        ) {}
    }
    Spacer(modifier = Modifier
        .padding(5.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun FoldersInterfaceContentPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
    }
}











