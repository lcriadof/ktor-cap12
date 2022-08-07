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


fun main(args: Array<String>) {
    io.ktor.server.tomcat.EngineMain.main(args) // [1]
}


fun Application.module() { // [2]
    configureSerialization() // [3]
    // configureRouting() // [4]
    configureSesionesEnServidor() // [5] gestion de las sesiones
    configureRoutingVerbos() // [6] gestion de verbos
}