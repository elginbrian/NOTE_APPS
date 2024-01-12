package com.example.note_apps.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.note_apps.component.ButtonFunction
import com.example.note_apps.component.TextFieldFunction
//import com.example.note_apps.data.NoteDataSource
import com.example.note_apps.model.NoteObject
import java.time.format.DateTimeFormatter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    notes: List<NoteObject>,
    onAddNote: (NoteObject) -> Unit,
    onRemoveNote: (NoteObject) -> Unit
){
    var title = remember {
        mutableStateOf("")
    }
    var description = remember {
        mutableStateOf("")
    }
    var expanded = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.onPrimary) {
        Scaffold(
            topBar = {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .padding(top = 30.dp, start = 10.dp, end = 10.dp)
                        .clickable { expanded.value = !expanded.value },
                    shape = RoundedCornerShape(50.dp),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary),
                    elevation = CardDefaults.cardElevation(10.dp)
                ){
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.Create, contentDescription = "Pen")
                        Spacer(modifier = Modifier.padding(3.dp))
                        Text(
                            text =
                                if (expanded.value == false){
                                    "Create a new note?"
                                } else {
                                    "NOTE APPS"
                                },
                            fontSize =
                            if (expanded.value == false){
                                18.sp
                            } else {
                                20.sp
                            },
                            fontWeight = FontWeight(500)
                        )
                    }
                }
            },

            content = {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Spacer(modifier = Modifier.padding(50.dp))

                    if (expanded.value == true){
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(15.dp)
                                .padding(start = 25.dp, end = 25.dp),
                            shape = RoundedCornerShape(15.dp),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {
                                TextFieldFunction(
                                    text = title.value, label = "Insert title here!",
                                    onTextChange = {
                                        if (it.all { char -> char.isDefined() || char.isWhitespace() }){
                                            title.value = it
                                        }
                                    }
                                )
                                TextFieldFunction(
                                    text = description.value, label = "Type your note here!",
                                    onTextChange = {
                                        if (it.all { char -> char.isDefined() || char.isWhitespace() }){
                                            description.value = it
                                        }
                                    }
                                )
                            }
                        }

                        ButtonFunction(
                            text = "Lorem Ipsum",
                            onClick = {
                                if(title.value.isNotEmpty() && description.value.isNotEmpty()){
                                    onAddNote(NoteObject(title = title.value, description = description.value))
                                    Toast.makeText(context, "${title.value} saved", Toast.LENGTH_SHORT).show()

                                    title.value = ""
                                    description.value = ""

                                    expanded.value = !expanded.value
                                }
                            }
                        )
                    } else {
                        Box(){}
                    }
                    Column(modifier = Modifier.fillMaxSize()) {
                        Spacer(modifier = Modifier.padding(5.dp))
                        Card (modifier = Modifier
                            .fillMaxWidth()
                            .height(3.dp)){}
                        LazyColumn(modifier = Modifier.fillMaxSize()){
                            items(notes){ note ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(180.dp)
                                        .padding(
                                            start = 25.dp,
                                            end = 25.dp,
                                            top = 15.dp,
                                            bottom = 5.dp
                                        ),
                                    shape = RoundedCornerShape(15.dp),
                                    elevation = CardDefaults.cardElevation(10.dp),
                                    //colors = CardDefaults.cardColors(MaterialTheme.colorScheme.inversePrimary)
                                ){
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                    ) {
                                        Card(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(40.dp),
                                            shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
                                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.inversePrimary)
                                        ) {
                                            Row(modifier = Modifier
                                                .fillMaxSize()
                                                .padding(start = 15.dp, end = 15.dp),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.SpaceBetween
                                            ) {
                                                Text(text = note.title, fontSize = 18.sp ,fontWeight = FontWeight(500))

                                                Row(verticalAlignment = Alignment.CenterVertically) {
                                                    Text(text = note.dateCreated.toString().substring(0, 10), fontSize = 12.sp)
                                                    Spacer(modifier = Modifier.padding(3.dp))
                                                    Icon(
                                                        imageVector = Icons.Default.Clear, contentDescription = "Delete",
                                                        modifier = Modifier.clickable {
                                                            onRemoveNote(note)
                                                        }
                                                    )
                                                }
                                            }
                                        }

                                        Column(modifier = Modifier
                                            .padding(8.dp)
                                            .fillMaxSize(),
                                            //verticalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(text = note.description, fontSize = 12.sp, maxLines = 8, textAlign = TextAlign.Justify, lineHeight = 13.sp)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewHomeScree(){
    val notes = remember {
        mutableStateListOf<NoteObject>()
    }
    HomeScreen(notes = notes, onAddNote = {notes.add(it)}, onRemoveNote = {notes.remove(it)})
}