package com.example

import freemarker.cache.*
import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.jackson.*
import io.ktor.features.*
import io.ktor.freemarker.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.util.*
import io.ktor.websocket.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import org.joda.time.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

//fun main(args: Array<String>) {
//    embeddedServer(Netty, commandLineEnvironment(args)).start(wait = true)
//}
@KtorExperimentalAPI
@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }

//    install(io.ktor.websocket.WebSockets) {
//        pingPeriod = Duration.ofSeconds(15)
//        timeout = Duration.ofSeconds(15)
//        maxFrameSize = Long.MAX_VALUE
//        masking = false
//    }

//    install(DefaultHeaders)
//    install(CallLogging)
//    install(WebSockets)

    install(ContentNegotiation) {
        jackson {
//            configure(SerializationFeature.INDENT_OUTPUT, true)
        }
    }

//    // database-related code
//    Database.connect(DatabaseFactory.dataSource)
//    install(FlywayFeature) {
//        dataSource = DatabaseFactory.dataSource
//    }

    Database.connect(
        url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
        driver = "org.h2.Driver"
    )

    transaction {
        SchemaUtils.create(Books)
    }

//    transaction {
////        Book.new {
////            name= "init"
////            desc= "desc"
////            DateTime.now()
////            DateTime.now()
////        }
//        Books.insert {
//            it[name]= "init"
//            it[desc]= "desc"
//            it[createdTime]= DateTime.now()
//            it[updatedTime]= DateTime.now()
//        }
//    }

//    val bookService = BookService()
//
//    install(Routing){
//        bookResource(bookService)
//    }

    val indexPage = javaClass.getResource("/index.html").readText()

    routing{

        get("/") {
            call.respondText(indexPage, ContentType.Text.Html)
//            call.respond(FreeMarkerContent("index.ftl", mapOf("data" to IndexData(listOf(1, 2, 3))), ""))
        }
        post("/book") {
            val book= call.receive<BookRequest>()

            transaction {
                Books.insert {
                    it[name]= book.name
                    it[desc]= book.desc
                    it[createdTime]= DateTime.now()
                    it[updatedTime]= DateTime.now()
                }
            }

            call.respond(book)
        }
        get("/book"){
            val books= transaction {
                Books.selectAll().map {
                    BookResponse(
                        it[Books.id].value,
                        it[Books.name],
                        it[Books.desc],
                        it[Books.createdTime].toString(),
                        it[Books.updatedTime].toString()
                    )
                }
            }
            call.respond(mapOf("data" to books))
        }
    }

//    val client = HttpClient(Apache) {
//    }
//
//    routing {
//        get("/") {
//            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
//        }
//
//        get("/html-dsl") {
//            call.respondHtml {
//                body {
//                    h1 { +"HTML" }
//                    ul {
//                        for (n in 1..10) {
//                            li { +"$n" }
//                        }
//                    }
//                }
//            }
//        }
//
//        get("/styles.css") {
//            call.respondCss {
//                body {
//                    backgroundColor = Color.red
//                }
//                p {
//                    fontSize = 2.em
//                }
//                rule("p.myclass") {
//                    color = Color.blue
//                }
//            }
//        }
//
//        get("/html-freemarker") {
//            call.respond(FreeMarkerContent("index.ftl", mapOf("data" to IndexData(listOf(1, 2, 3))), ""))
//        }

//        webSocket("/myws/echo") {
//            send(Frame.Text("Hi from server"))
//            while (true) {
//                val frame = incoming.receive()
//                if (frame is Frame.Text) {
//                    send(Frame.Text("Client said: " + frame.readText()))
//                }
//            }
//        }

//        get("/json/jackson") {
//            call.respond(mapOf("hello" to "world"))
//        }
//    }
}


//
//fun FlowOrMetaDataContent.styleCss(builder: CSSBuilder.() -> Unit) {
//    style(type = ContentType.Text.CSS.toString()) {
//        +CSSBuilder().apply(builder).toString()
//    }
//}
//
//fun CommonAttributeGroupFacade.style(builder: CSSBuilder.() -> Unit) {
//    this.style = CSSBuilder().apply(builder).toString().trim()
//}
//
//suspend inline fun ApplicationCall.respondCss(builder: CSSBuilder.() -> Unit) {
//    this.respondText(CSSBuilder().apply(builder).toString(), ContentType.Text.CSS)
//}
