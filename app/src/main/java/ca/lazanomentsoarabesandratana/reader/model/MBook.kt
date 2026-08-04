package ca.lazanomentsoarabesandratana.reader.model

data class MBook(
    var id: String? = null,
    var title: String? = null,
    var authors: String? = null,
    var notes: String? = null,
    var imageLinks: String? = null,
    var stars: Int = 0,
    var description: String? = null
)
