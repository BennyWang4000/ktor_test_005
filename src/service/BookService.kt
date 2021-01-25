package com.example.service

//import com.example.*
//import com.example.service.DatabaseFactory.dbQuery
//import org.jetbrains.exposed.sql.*

class BookService {
//    suspend fun getAllBooks(): List<Book> = dbQuery{
//        Books.selectAll().map {
//            toBook(it)
//        }
//    }
//
//    suspend fun getBook(id: Int): Book?= dbQuery{
//        Books.select{
//            (Books.id eq id)
//        }.mapNotNull { toBook(it) }
//            .singleOrNull()
//    }
//
////    suspend fun addBook(book: Book): Book{
////        var key= 0
////        dbQuery {
////            key= (Books.insert {
////                it[id]= book.id
////                it[name]= book.name
////                it[desc]= book.desc
////            } get Books.id)
////        }
////        return getBook(key)!!
////    }
//
//    private fun toBook(row: ResultRow): BookResponse =
//        BookResponse(
//            row[Books.value],
//            row[Books.name],
//            row[Books.desc],
//            row[Books.createdTime],
//            row[Books.updatedTime]
//        )
}