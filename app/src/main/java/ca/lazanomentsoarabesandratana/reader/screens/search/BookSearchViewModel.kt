package ca.lazanomentsoarabesandratana.reader.screens.search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.util.query
import ca.lazanomentsoarabesandratana.reader.data.DataOrException
import ca.lazanomentsoarabesandratana.reader.model.MBook
import ca.lazanomentsoarabesandratana.reader.repository.BookRepository
import coil.util.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookSearchViewModel @Inject constructor(private val repository: BookRepository) :
    ViewModel() {
    var listOfBooks: MutableState<DataOrException<List<MBook>, Boolean, Exception>> =
        mutableStateOf(
            DataOrException(null, true, Exception(""))
        )


    init {
        searchBooks()
    }

    fun searchBooks() {
        //viewModelScope.launch(Dispatchers.IO) {
        viewModelScope.launch() {
            // listOfBooks.value.loading = true
            listOfBooks.value = repository.getBooks()

            Log.d("DATA", "searchBooks: ${listOfBooks.value.data.toString()}")

            if (listOfBooks.value.data.toString().isNotEmpty()) listOfBooks.value.loading = false
        }
    }


}