package com.example

import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.dao.id.*
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.jodatime.datetime

object Books: IntIdTable() {
    val name= varchar("name", 127)
    val desc= varchar("desc", 255)
    val createdTime= datetime("created_time")
    val updatedTime= datetime("updated_time")
}

class Book(id: EntityID<Int>): IntEntity(id){
    companion object: IntEntityClass<Book>(Books)

    var name= Books.name
    var desc= Books.desc
    var createdTime= Books.createdTime
    var updatedTime= Books.updatedTime
}

data class BookRequest(
    val name: String,
    val desc: String
)

data class BookResponse(
    val id: Int,
    val name: String,
    val desc: String,
    val createdTime: String,
    val updatedTime: String
)