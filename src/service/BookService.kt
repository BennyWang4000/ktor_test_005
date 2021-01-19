package com.example.service

import com.example.Book
import com.example.Books
import com.example.service.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class BookService {
    suspend fun getAllBooks(): List<Book> = dbQuery{
        Books.selectAll().map {
            toBook(it)
        }
    }

    suspend fun getBooksById(id: Int): Book?= dbQuery{
        Books.select{
            (Books.id eq id)
        }.mapNotNull { toBook(it) }
            .singleOrNull()
    }

    private fun toBook(row: ResultRow): Book =
        Book(
            row[Books.id],
            row[Books.name],
            row[Books.desc],
        )
}