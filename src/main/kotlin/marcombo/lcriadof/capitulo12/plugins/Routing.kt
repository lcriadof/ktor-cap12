package marcombo.lcriadof.capitulo12.plugins

/*
El gran libro de Kotlin
(para programadores de back end)
Editorial: Marcombo (https://www.marcombo.com/)
Autor: Luis Criado Fernández (http://luis.criado.online/)
CAPÍTULO 12: Aplicaciones war con ktor.
 */
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {



    routing {
        get("/") {
            call.respondText("¡Hola Mundo!")
        }
        get("/listaHasta{num}") {  // http://127.0.0.1:8081/listaHasta?num=22
            val num2=call.parameters["num"]!!.toInt()
            var salida=""
            for (i in 1..num2){
                salida=salida+" "+i
            }
          call.respond("La lista es: $salida")
        }




    }
}
