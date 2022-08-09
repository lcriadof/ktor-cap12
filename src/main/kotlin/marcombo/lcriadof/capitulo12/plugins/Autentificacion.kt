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
import io.ktor.server.auth.*
import java.security.*
import kotlin.text.Charsets.UTF_8

fun getMd5Digest(str: String): ByteArray = MessageDigest.getInstance("MD5").digest(str.toByteArray(UTF_8))  // [1]

val myRealm = "Access to the '/' path" // [2]

// bloque [3]
val userTable: Map<String, ByteArray> = mapOf(
    "jetbrains" to getMd5Digest("jetbrains:$myRealm:foobar"),
    "lcriadof" to getMd5Digest("lcriadof:$myRealm:prueba1234"),
    "admin" to getMd5Digest("admin:$myRealm:password")
)
// fin de [3]

fun Application.configureAuthentication() {

    // bloque [4]
    install(Authentication) {
        digest("auth-digest") {
            realm = myRealm
            digestProvider { userName, realm ->
                userTable[userName]
            }
        }
    }
    // bloque [4]


    // bloque [5]
    routing {
        authenticate("auth-digest") {
            get("/autentificar") {
                call.respondText("Hola, ${call.principal<UserIdPrincipal>()?.name}!")
            }
        }
    }
    // bloque [5]
}