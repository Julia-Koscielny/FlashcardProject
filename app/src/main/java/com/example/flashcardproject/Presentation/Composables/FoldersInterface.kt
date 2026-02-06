package com.example.flashcardproject.Presentation.Composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashcardproject.Data.Resources.Icons
import com.example.flashcardproject.Presentation.Folder.FolderUi
import com.example.flashcardproject.Presentation.Folder.FolderViewModel
import com.example.flashcardproject.R
import com.example.flashcardproject.ui.theme.PurpleGrey40
import com.example.flashcardproject.ui.theme.PurpleGrey80


// FOLDERS INTERFACE
@Composable
fun FoldersInterfaceScreen(
    viewModel: FolderViewModel = viewModel()
) {
    val uiState by viewModel.flashCardsAppUiState.collectAsState()

        FoldersInterfaceContent(
            folderList = uiState.folders,
            onAddFolder = {
                viewModel.addFolder(name = "New folder", id = 0, icon = Icons.FOLDER)
            },
            onDeleteFolder = { id ->
                viewModel.deleteFolder(id)
            }
        )

    }


@Composable
private fun FoldersInterfaceContent(
    folderList: List<FolderUi>,
    onAddFolder: () -> Unit,
    onDeleteFolder: (Long) -> Unit
){

    Column(modifier = Modifier
        .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        LazyColumn {
            items(items = folderList,
                key = { it.id }){
                    folder ->
                FolderMin(
                    folder = folder,
                    onDeleteFolder = {onDeleteFolder(folder.id)}
                )
            }
        }

        Button(onClick = onAddFolder) {
            Text("Add folder")
        }
    }

}

@Composable
fun FolderMin(folder: FolderUi,
              onDeleteFolder: () -> Unit){

    Row( modifier = Modifier
        .background(PurpleGrey80, RectangleShape)
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .heightIn(max = 60.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(folder.icon.res), "",
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
            onClick = { onDeleteFolder() }
        ) {
            Image(painter =painterResource(R.drawable.menudotsvertical), "Options",
                modifier = Modifier )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FoldersInterfaceContentPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        FoldersInterfaceContent(
            folderList = listOf(
                FolderUi(1, "Docs", Icons.FOLDER),
                FolderUi(2, "Flowers", Icons.FLOWER)
            ),
            onAddFolder = {},
            onDeleteFolder = {}

        )
    }
}











