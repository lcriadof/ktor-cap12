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


data class vConjugado(val yo:String,val tu:String, val el:String, val nosotros:String, val vosotros:String, val ellos:String)
data class rootVerbo (val verbo:String, val forma:String, val conjugacion:vConjugado)
data class mensaje (val texto:String)
data class verbo (val etiquetaverbo:String, val verbo:String)

var verbosConjugados: MutableList<rootVerbo> = mutableListOf()
var listaDeverbosSoportados: MutableList<String> = mutableListOf( "calcular","cambiar","caminar","cancelar",
    "cansarse","cantar", "caracterizar","casar","celebrar","cenar","coincidir", "comer",
    "consistir", "correr", "corresponder", "cortar", "cumplir", "curiosear",
    "abastar", "ser" )


fun Application.configureRoutingVerbos() {


    routing {
        route("/verbo"){

            get("/conjugar") {  // http://127.0.0.1:8080/verbo
                  call.respond( verbosConjugados ) // devolvemos JSON

            } // fin de get


            put("/add0{verb}"){
                val v=call.parameters["verb"]!!.toString()



                if  ( (listaDeverbosSoportados.find { e -> e.toString().equals(v) } !=  null) // lo ha encontrado
                    &&  (  verbosConjugados.find{ z -> z.verbo.equals(v)  } ==  null ) )  // y no la tenemos ya                           )
                {

                    // conjugamos
                    var v1 = indicativoPresente(v, 0) // declaramos la conjugación
                    var presente = Array<String>(6, { i -> "" }) // inicializamos array donde obtendremos la conjugación de trabajo
                    var x = 0
                    v1.conjugarForma() // conjugamos el presente de indicativo

                    v1.formaConjugada.forEach { it -> // recuperamos las formas conjugadas
                        presente[x] = it
                        x++
                    }

                    var v3: vConjugado = vConjugado(presente[0],
                        presente[1],
                        presente[2],
                        presente[3],
                        presente[4],
                        presente[5]) // cargamos datos en data class

                    var v2: rootVerbo = rootVerbo(v, "Presente indicativo", v3)


                    verbosConjugados.add(v2) // añadimos datos a la lista mutable
                    // fin de conjugación



                    call.respond(mensaje("El verbo [$v] se ha incluido en esta sesión")) // devolvemos JSON
                }else{
                    call.respond(mensaje("El verbo [$v] no está soportado actualmente o ya está añadido ")) // devolvemos JSON
                }
            }  // fin de put

            // opcion 2
            put("/add1"){ // en body
                val v=call.receive<verbo>().verbo

                if  ( (listaDeverbosSoportados.find { e -> e.toString().equals(v) } !=  null) // lo ha encontrado
                    &&  (  verbosConjugados.find{ z -> z.verbo.equals(v)  } ==  null ) )  // y no la tenemos ya                           )
                {

                    // conjugamos
                    var v1 = indicativoPresente(v, 0) // declaramos la conjugación
                    var presente = Array<String>(6, { i -> "" }) // inicializamos array donde obtendremos la conjugación de trabajo
                    var x = 0
                    v1.conjugarForma() // conjugamos el presente de indicativo

                    v1.formaConjugada.forEach { it -> // recuperamos las formas conjugadas
                        presente[x] = it
                        x++
                    }

                    var v3: vConjugado = vConjugado(presente[0],
                        presente[1],
                        presente[2],
                        presente[3],
                        presente[4],
                        presente[5]) // cargamos datos en data class

                    var v2: rootVerbo = rootVerbo(v, "Presente indicativo", v3)


                    verbosConjugados.add(v2) // añadimos datos a la lista mutable
                    // fin de conjugación



                    call.respond(mensaje("El verbo [$v] se ha incluido en esta sesión")) // devolvemos JSON
                }else{
                    call.respond(mensaje("El verbo [$v] no está soportado actualmente o ya está añadido ")) // devolvemos JSON
                }
            }  // fin de put



            delete() {
                //val v = call.parameters["verb"]!!.toString()
                val v = call.receive<verbo>().verbo
                //call.respond(mensaje("recibido ${v.verbo}"))


                var n=0
               // if ( listaDeverbosConjugados.find { e -> e.toString().equals(v.verbo) } != null) { // lo ha encontrado
                if (  verbosConjugados.find{ z -> z.verbo.equals(v)  } !=  null ) { // lo ha encontrado

                    // bloque para borrar de verbosConjugados
                    var indiceAborrar=0
                    n=0
                    verbosConjugados.forEach{b ->
                        if (b.verbo.equals(v)){
                            indiceAborrar=n // [se indica el indice a borrar
                        }
                        n++
                    }
                    verbosConjugados.removeAt(indiceAborrar)
                    // fin de borrar en verbosConjugados

                    call.respond(mensaje("El verbo [${v}] ha sido borrado para esta sesión")) // devolvemos JSON
                }else{
                    call.respond(mensaje("El verbo [${v}] no se ha encontrado en esta sesión")) // devolvemos JSON

                }


            } // fin delete


        } // fin de route

    } // fin de routing
}