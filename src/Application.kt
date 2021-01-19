package com.example

import com.example.service.BookService
import com.example.service.DatabaseFactory
import com.example.web.bookResource
import io.ktor.application.*
import io.ktor.routing.*
import com.fasterxml.jackson.databind.*
import com.viartemev.ktor.flyway.FlywayFeature
import io.ktor.jackson.*
import io.ktor.features.*
import io.ktor.util.*
import org.jetbrains.exposed.sql.Database

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@KtorExperimentalAPI
@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
//    install(FreeMarker) {
//        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
//    }

//    install(io.ktor.websocket.WebSockets) {
//        pingPeriod = Duration.ofSeconds(15)
//        timeout = Duration.ofSeconds(15)
//        maxFrameSize = Long.MAX_VALUE
//        masking = false
//    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
    // database-related code
    Database.connect(DatabaseFactory.dataSource)

    install(FlywayFeature) {
        dataSource = DatabaseFactory.dataSource
    }

    val bookService = BookService()

    install(Routing){
        bookResource(bookService)
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
