package ca.lazanomentsoarabesandratana.reader.network

import ca.lazanomentsoarabesandratana.reader.model.MBook
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface BooksApi {

    @GET("books/book")
    suspend fun getAllBooks(): List<MBook>

    @GET("books/book/{id}")
    suspend fun getBookById(@Path("id") id: String): MBook

}