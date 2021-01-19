package com.example.web

import com.example.service.BookService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.bookResource(bookService: BookService) {
    route("/"){
        get{
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain) }
        get("data"){

        }
        get("data/{id}"){

        }

    }
}