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
import io.ktor.server.routing.*

data class autor(val nombre: String, val website: String) // [1]

fun Application.configureSerialization() { // [2]
    install(ContentNegotiation) { // [3]
        gson { // [4]
            setPrettyPrinting() // [5] para que lo muestre bien formateado
        }
    }

    routing {
        get("/json/gson1") { // [6] http://127.0.0.1:8080//json/gson1
            call.respond(mapOf("Hola" to "mundo")) // [7]
        }

        get("/json/gson2") { // [8] http://127.0.0.1:8080//json/gson2
            val a = autor("Luis Criado", "http://luis.criado.online/index.html") // [9]
            call.respond(a) // [10]
        }

    }
}
