package com.emily.note.presentation.add_edit_note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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

@OptIn(ExperimentalMaterial3Api::class)
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

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFDBB8CF)),
        containerColor = Color(0xFFDBB8CF),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(
                        AddEditNoteEvent.OnSaveNote
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    ) { padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            TextField(
                value = viewModel.state.value.title,
                onValueChange = { value ->
                    viewModel.onEvent(
                        AddEditNoteEvent.OnChangeTitle(
                            value
                        )
                    )
                },
                placeholder = {
                    Text(
                        "Title",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xB26A4C71)
                    )
                },
                textStyle = TextStyle(
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xB26A4C71)
                ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = viewModel.state.value.content,
                onValueChange = { value ->
                    viewModel.onEvent(
                        AddEditNoteEvent.OnChangeContent(
                            value
                        )
                    )
                },
                placeholder = {
                    Text(
                        text = "Enter text...",
                        fontSize = 24.sp,
                        color = Color(0xB26A4C71)
                    )
                },
                textStyle = TextStyle(
                    fontSize = 24.sp,
                    color = Color(0xB26A4C71)
                ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        }
    }
}

