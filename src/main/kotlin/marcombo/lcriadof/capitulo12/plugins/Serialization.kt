package marcombo.lcriadof.capitulo12.plugins
/*
El gran libro de Kotlin
(para programadores de back end)
Editorial: Marcombo (https://www.marcombo.com/)
Autor: Luis Criado Fernández (http://luis.criado.online/)
CAPÍTULO 12: Aplicaciones war con ktor.
 */
import io.ktor.serialization.gson.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

data class autor(val nombre: String, val website: String)



fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting() // para que lo muestre bien formateado
        }
    }

    routing {
        get("/json/gson1") { // http://127.0.0.1:8080//json/gson1
            call.respond(mapOf("Hola" to "mundo"))
        }

        get("/json/gson2") { // http://127.0.0.1:8080//json/gson2
            val author = autor("Luis Criado", "http://luis.criado.online/index.html")
            call.respond(author)
        }

    }
}
