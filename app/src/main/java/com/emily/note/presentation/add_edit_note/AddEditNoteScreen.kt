package com.emily.note.presentation.add_edit_note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun AddEditNoteScreen (
    viewModel: AddEditNoteViewModel = hiltViewModel(),
    navController: NavController
) {

    LaunchedEffect(key1 = true) {
        viewModel.navigate.collect { screen ->
            navController.navigate(screen.route)
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color(0xFFB998AE)
    ) {
        Column {
            TextField(
                label = {
                    Text(text = "Title")
                },
                value = viewModel.state.value.title,
                onValueChange = { value ->
                    viewModel.onEvent(
                        AddEditNoteEvent.OnChangeTitle(
                            value
                        )
                    )
                },
                textStyle = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xB26A4C71)
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                label = {
                    Text(text = "Enter text...")
                },
                value = viewModel.state.value.content,
                onValueChange = { value ->
                    viewModel.onEvent(
                        AddEditNoteEvent.OnChangeContent(
                            value
                        )
                    )
                },
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xB26A4C71)
                )
            )

            IconButton(
                onClick = {
                    viewModel.onEvent(
                        AddEditNoteEvent.OnSaveNote
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = null
                )
            }
        }
    }
}

