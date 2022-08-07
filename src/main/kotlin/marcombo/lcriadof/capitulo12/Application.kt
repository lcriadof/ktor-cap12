package marcombo.lcriadof.capitulo12

/*
El gran libro de Kotlin
(para programadores de back end)
Editorial: Marcombo (https://www.marcombo.com/)
Autor: Luis Criado Fernández (http://luis.criado.online/)
CAPÍTULO 12: Aplicaciones war con ktor.
 */


import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
//import io.ktor.server.netty.*
import io.ktor.server.tomcat.*
import marcombo.lcriadof.capitulo12.plugins.*

/*
fun main() {

  //  embeddedServer(Netty, port = 8081, host = "127.0.0.1") {
    embeddedServer(Tomcat, port = 8081, host = "127.0.0.1") {
        configureSerialization()
        configureRouting()
    }.start(wait = true)
}
 */


fun main(args: Array<String>) {
    io.ktor.server.tomcat.EngineMain.main(args)
}


fun Application.module() {
    configureSerialization()
    configureRouting()
    configureSesionesEnServidor() // gestion de las sesiones
    configureRoutingVerbos() // gestion de verbos

}