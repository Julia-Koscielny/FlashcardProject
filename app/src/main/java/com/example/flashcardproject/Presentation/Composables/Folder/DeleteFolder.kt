package com.example.flashcardproject.Presentation.Composables.Folder

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flashcardproject.Presentation.Folder.FolderViewModel

@Composable
fun DeleteFolderScreen(
    viewModel: FolderViewModel,
    folderId: Long,
    onDeleted: () -> Unit,
    onCancel: ()-> Unit
) {

    DeleteFolderContent(
        onDeleteFolder = {
            viewModel.deleteFolder(folderId)
            onDeleted()
        },
        onCancel = onCancel
    )

}

@Composable
fun DeleteFolderContent(
    onDeleteFolder: () -> Unit,
    onCancel: () -> Unit){

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        Column(
            modifier = Modifier
                .padding(5.dp)
                .widthIn(max = 200.dp)
        ) {
            Text("Do you want to delete the folder?")
            Spacer(modifier = Modifier.padding(5.dp))

            Row(modifier = Modifier.padding(5.dp)) {

                Button(onClick = onDeleteFolder) { Text("Delete") }

                Spacer(modifier = Modifier.weight(1f))

                Button(onClick = onCancel) { Text("Cancel")}
            }
        }

    }


}

@Preview(showBackground = true)
@Composable
fun DeleteFolderContentReview(){
}