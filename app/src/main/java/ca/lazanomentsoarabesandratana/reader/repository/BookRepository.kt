package ca.lazanomentsoarabesandratana.reader.repository

import ca.lazanomentsoarabesandratana.reader.data.DataOrException
import ca.lazanomentsoarabesandratana.reader.model.MBook
import ca.lazanomentsoarabesandratana.reader.network.BooksApi
import javax.inject.Inject

class BookRepository @Inject constructor(private val api: BooksApi) {
    private val dataOrException = DataOrException<List<MBook>, Boolean, Exception>()
    private val dataOrExceptionSingleBook = DataOrException<MBook, Boolean, Exception>()

    suspend fun getBooks(): DataOrException<List<MBook>, Boolean, Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = api.getAllBooks()
            if (dataOrException.data!!.isNotEmpty()) dataOrException.loading = false

        } catch (e: Exception) {
            dataOrException.e = e
        }
        return dataOrException
    }

    suspend fun getBookById(id: String): DataOrException<MBook, Boolean, Exception> {
        val response = try {
            dataOrExceptionSingleBook.loading = true
            dataOrExceptionSingleBook.data = api.getBookById(id)
            if (dataOrExceptionSingleBook.data.toString()
                    .isNotEmpty()
            ) dataOrExceptionSingleBook.loading = false
            else {
            }
        } catch (e: Exception) {
            dataOrExceptionSingleBook.e = e
        }

        return dataOrExceptionSingleBook
    }
}