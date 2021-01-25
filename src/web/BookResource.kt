package com.example.web

import com.example.*
import com.example.service.BookService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.bookResource(bookService: BookService) {
//    route("/"){
//        get{
//            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
//        }
//    }
//
//    route("/book"){
//        get("/"){
//            call.respond(bookService.getAllBooks())
//        }
//
//        get("/{id}"){
//            val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("Must provide id")
//            val book= bookService.getBook(id)?.let {
//                call.respond(it)
//            }?: run {
//                call.respond(HttpStatusCode.NotFound)
//            }
//        }
//
//        post("/"){
//            val book= call.receive<Book>()
//            call.respond(HttpStatusCode.Created, bookService.addBook(book))
//        }
//    }
}