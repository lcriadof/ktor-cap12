# ktor-cap12

En este proyecto, las diferentes partes están implementadas independientemente con la finalidad de la simplicidad.
Dicho de otra forma, la parte de verbos no requiere ni envio de sesión ni autentificación digest


    PUT  http://127.0.0.1:8080/verbo/add1    
    con body(raw, JSON)
    {
      "verbo": "cantar"    
    }
    
    
    

    PUT  http://127.0.0.1:8080/verbo/add1
    con body(raw, JSON)    
    {
      "verbo": "ser"
    }
      
    PUT  http://127.0.0.1:8080/verbo/conjugar
    devuelve:
    [
    {
    "verbo": "cantar",
    "forma": "Presente indicativo",
    "conjugacion": {
      "yo": "canto",
      "tu": "cantas",
      "el": "canta",
      "nosotros": "cantamos",
      "vosotros": "cantáis",
      "ellos": "cantan"
    }
    },
    {
    "verbo": "ser",
    "forma": "Presente indicativo",
    "conjugacion": {
      "yo": "soy",
      "tu": "eres",
      "el": "es",
      "nosotros": "somos",
      "vosotros": "sois",
      "ellos": "son"
    }
    }
    ]
  

Para probar el servicio REST es útil utilizar la herrramienta [PostMan](https://www.postman.com/downloads/). Aqui dejo las colecciones de ejemplo, que puedes importarlo y ejecutarlo desde PostMan

  COLECCIÓN PROBAR: [desde IDE](https://github.com/lcriadof/ktor-cap12/blob/master/postman/colecciones/ktor_cap12%20desde%20IDE.postman_collection.zip)

  COLECCIÓN PROBAR: [desde tomcat o docket-tomcat](https://github.com/lcriadof/ktor-cap12/blob/master/postman/colecciones/ktor_cap12%20despleguado%20en%20tomcat%20o%20docker.postman_collection.zip)
  
  COLECCIÓN PROBAR: [autentificación](https://github.com/lcriadof/ktor-cap12/blob/master/postman/colecciones/Probar%20ktor-cap12_autentificacion.postman_collection.json)

  COLECCIÓN PARA PROBAR en una instalación real de TOMCAT o un DOCKER con TOMCAT: [conjugaciones, sesiones y autentificación](https://github.com/lcriadof/ktor-cap12/blob/master/postman/colecciones/despliegue%20en%20tomcat%20o%20docker.postman_collection.json)

-

  SOBRE EL [AUTOR](http://luis.criado.online/) 	
  
  IR AL INDICE GENERAL: [EL GRAN LIBRO DE KOTLIN](https://github.com/Marcombo/El-gran-libro-de-Kotlin)
