package com.example.note_apps.screen

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.example.note_apps.data.NoteDataSource
import com.example.note_apps.model.NoteObject
import com.example.note_apps.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    private val _noteList = MutableStateFlow<List<NoteObject>>(emptyList())
    val noteList = _noteList.asStateFlow()
    //private var noteList = mutableStateListOf<NoteObject>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNote().distinctUntilChanged().collect{
                listOfNotes -> if(listOfNotes.isNullOrEmpty()){

                } else {
                    _noteList.value = listOfNotes
                }
            }
        }
    }

    fun addNote(note: NoteObject)      = viewModelScope.launch { repository.addNote(note)    }
    fun removeNote(note: NoteObject)   = viewModelScope.launch { repository.deleteNote(note) }
    fun updateNote(note: NoteObject)   = viewModelScope.launch { repository.updateNote(note) }
}