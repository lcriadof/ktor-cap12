package marcombo.lcriadof.capitulo12.plugins

/*
El gran libro de Kotlin
(para programadores de back end)
Editorial: Marcombo (https://www.marcombo.com/)
Autor: Luis Criado Fernández (http://luis.criado.online/)
CAPÍTULO 12: Aplicaciones war con ktor.
 */

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

// ejemplo Hola Mundo
fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hola Mundo")
        }

    }
}


