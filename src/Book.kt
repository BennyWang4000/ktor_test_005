package com.example

import org.jetbrains.exposed.sql.Table

object Books: Table() {
    val id= integer("id")
    val name= varchar("name", 127)
    val desc= varchar("name", 255)
}

data class Book(
    val id: Int,
    val name: String,
    val desc: String
)