package com.seachange.digressions

class Book(val id: String, val title: String)

fun loadBookById(id: String): Book? {
    // do some loading stuff
    return Book(id, "Kotlin in Action")
}

fun main() {
    val book: Book? = loadBookById("123456789")

    if( book != null ) {
        println(book.title)
    }
    book?.let {
        println(it.title)
    }
}
