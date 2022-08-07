package marcombo.lcriadof.capitulo12.plugins

/*
El gran libro de Kotlin
(para programadores de back end)
Editorial: Marcombo (https://www.marcombo.com/)
Autor: Luis Criado Fernández (http://luis.criado.online/)
CAPÍTULO 12: Aplicaciones war con ktor.
 */
import io.github.lcriadof.sofia.gramatica.castellano.verbos.indicativoPresente
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

// bloque [a]
data class vConjugado(val yo:String,val tu:String, val el:String, val nosotros:String, val vosotros:String, val ellos:String)
data class rootVerbo (val verbo:String, val forma:String, val conjugacion:vConjugado)
data class mensaje (val texto:String)
data class verbo (val etiquetaverbo:String, val verbo:String)
// fin de bloque [a]

fun Application.configureRoutingVerbos() {
//  bloque [b]
    var verbosConjugados: MutableList<rootVerbo> = mutableListOf()
    var listaDeverbosSoportados: MutableList<String> = mutableListOf( "calcular","cambiar","caminar","cancelar",
        "cansarse","cantar", "caracterizar","casar","celebrar","cenar","coincidir", "comer",
        "consistir", "correr", "corresponder", "cortar", "cumplir", "curiosear",
        "abastar", "ser" )
// fin de bloque [b]

    routing { // [1]
        route("/verbo"){ // [2]

            get("/conjugar") {  // [3]
                 call.respond( verbosConjugados ) // [4] devolvemos JSON

            } // fin de get


            // sección de PUT
            // opcion 1: paso por parámetro
            /*
            put("/add0{verb}"){ // [5] paso por parámetro
                val v=call.parameters["verb"]!!.toString() // [6]
            */


            // opcion 2: paso por body
            put("/add1"){ // [7] paso por body
                val v=call.receive<verbo>().verbo // [8]

                // [9] if
                if  ( (listaDeverbosSoportados.find { e -> e.toString().equals(v) } !=  null) // lo ha encontrado
                    &&  (  verbosConjugados.find{ z -> z.verbo.equals(v)  } ==  null ) )  // y no la tenemos ya                           )
                {

                    // conjugamos
                    var v1 = indicativoPresente(v, 0) // [10]
                    var presente = Array<String>(6, { i -> "" }) // [11]
                    var x = 0
                    v1.conjugarForma() // [12]

                    v1.formaConjugada.forEach { it -> // [13]
                        presente[x] = it
                        x++
                    }

                    var v3: vConjugado = vConjugado(presente[0],
                        presente[1],
                        presente[2],
                        presente[3],
                        presente[4],
                        presente[5]) // [14]

                    var v2: rootVerbo = rootVerbo(v, "Presente indicativo", v3) // [15]


                    verbosConjugados.add(v2) // [16]
                    // fin de conjugación


                    call.respond(mensaje("El verbo [$v] se ha incluido en esta sesión")) // [17]
                }else{
                    call.respond(mensaje("El verbo [$v] no está soportado actualmente o ya está añadido ")) // [18]]
                }
            }  // fin de put



            delete() {
                val v = call.receive<verbo>().verbo // [19]

                var n=0
                if (  verbosConjugados.find{ z -> z.verbo.equals(v)  } !=  null ) { // [20]

                    // bloque para borrar de verbosConjugados
                    var indiceAborrar=0
                    n=0
                    verbosConjugados.forEach{b ->
                        if (b.verbo.equals(v)){
                            indiceAborrar=n // [21]
                        }
                        n++
                    }
                    verbosConjugados.removeAt(indiceAborrar) // [22]
                    // fin de borrar en verbosConjugados

                    call.respond(mensaje("El verbo [${v}] ha sido borrado para esta sesión"))
                }else{
                    call.respond(mensaje("El verbo [${v}] no se ha encontrado en esta sesión"))

                }


            } // fin delete


        } // fin de route

    } // fin de routing
}