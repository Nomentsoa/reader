package ca.lazanomentsoarabesandratana.reader.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.lazanomentsoarabesandratana.reader.data.DataOrException
import ca.lazanomentsoarabesandratana.reader.model.MBook
import ca.lazanomentsoarabesandratana.reader.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: BookRepository) : ViewModel() {
    suspend fun getBookInfo(bookId: String): DataOrException<MBook, Boolean, Exception> {
        return repository.getBookById(bookId)
    }
}