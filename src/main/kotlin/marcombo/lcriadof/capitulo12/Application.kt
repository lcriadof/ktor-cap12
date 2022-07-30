package marcombo.lcriadof.capitulo12

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
}