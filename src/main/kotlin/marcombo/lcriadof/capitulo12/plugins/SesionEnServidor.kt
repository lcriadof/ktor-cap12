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
import io.ktor.server.sessions.*
import io.ktor.util.*
import java.io.*



//data class CartSession(val userID: String, var verbosConjugados: MutableList<rootVerbo>)

data class CartSession(val userID: String, var productIDs: MutableList<Int>)




fun Application.configureSesionesEnServidor() {
    var numeroUsurActivos=0
    var parar=false



    install(Sessions) {
        val secretSignKey = hex("6819b57a326945c1968f45236589")
        header<CartSession>("cart_session", directorySessionStorage(File("build/.sessions"))) {
            transform(SessionTransportTransformerMessageAuthentication(secretSignKey))
        }
    }
    routing {
        get("/login") {
            if (parar==false){
                call.sessions.set(CartSession(userID = "123", productIDs = mutableListOf(1, 3, 7)))
                val sesionacrual=call.sessions.get<CartSession>()
                numeroUsurActivos++
                call.respond(mensaje("Acceso concedido ${call.sessions.get<CartSession>()}"))
            }else{
                call.respond(mensaje("El servidor no acepta más usuarios"))
            }
        }

        get("/cart") {
            val cartSession = call.sessions.get<CartSession>()
            if (cartSession != null) {
                call.respond(mensaje("Product IDs: ${cartSession.productIDs}"))
            } else {
                call.respond(mensaje("No hay información."))
            }
        }

        get("/logout") {
            val cartSession = call.sessions.get<CartSession>()
            if (cartSession != null) {
                call.sessions.clear<CartSession>()
                call.respond(mensaje("Sesión cerrada"))
                numeroUsurActivos--
            } else {
                call.respond(mensaje("Sesión inexistente"))
            }
        }

        get("/activas") {
            call.respond(mensaje("Sesiones activas $numeroUsurActivos")) // devolvemos JSON

        }

        get("/parar") {
            parar=true
            call.respond(mensaje("Se activado la orden para no aceptar más sesiones")) // devolvemos JSON
        }

    } // fin de routing

} // fin de Application.configureSesionesEnServidor()