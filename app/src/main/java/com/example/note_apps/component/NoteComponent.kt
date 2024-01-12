package com.example.note_apps.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TextFieldFunction(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit ={}
){
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(textColor = MaterialTheme.colorScheme.inverseSurface),
        maxLines = maxLine,
        label = { Text(text = label) },

        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
        ),

        keyboardActions = KeyboardActions(onDone = {
            onImeAction
            keyboardController?.hide()
        }),

        modifier = Modifier
    )
}

@Composable
fun ButtonFunction(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
){
    Card(
        modifier = Modifier
            .width(70.dp)
            .height(40.dp),
        shape = RoundedCornerShape(50.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary),
        elevation = CardDefaults.cardElevation(10.dp)
    ){
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onClick, modifier = Modifier.fillMaxSize()) {
                Icon(imageVector = Icons.Default.Done, contentDescription = "Pen")
            }
        }
    }
}