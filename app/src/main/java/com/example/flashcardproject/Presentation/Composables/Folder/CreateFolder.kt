package com.example.flashcardproject.Presentation.Composables.Folder

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flashcardproject.Presentation.Folder.FolderViewModel
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import com.example.flashcardproject.Presentation.Folder.FolderUi

@Composable
fun CreateFolderScreen(
    viewModel: FolderViewModel,
    onFolderCreated: () -> Unit,
) {
    CreateFolderContent(
        onCreateFolder = { name ->
            viewModel.addFolder(
                FolderUi(
                    id = 0,
                    name = name
                )
            )
            onFolderCreated()
        }
    )
}

@Composable
fun CreateFolderContent(
    onCreateFolder: (String) -> Unit,
){
    var name by remember { mutableStateOf("") }

    Box (modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center){
        Column(modifier = Modifier.
        padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally){
            TextField(
                value = name,
                onValueChange = {name =it},
                label = {Text("Give folder name")}
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Button(
                onClick = {if (name.isNotBlank()) {
                    onCreateFolder(name)
                    name = ""}
                })
            {
                Text("Submit")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateFolderContentPreview() {
    Box(modifier = Modifier.fillMaxSize())
    CreateFolderContent(
        onCreateFolder = {}
    )
}

