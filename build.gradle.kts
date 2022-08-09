
val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    application
    kotlin("jvm") version "1.7.10"
    id("org.gretty") version "3.0.6"
    id("war")
}

group = "marcombo.lcriadof.capitulo12"
version = "1.2"

application {
    mainClass.set("marcombo.lcriadof.capitulo12.ApplicationKt")
    //mainClass.set("io.ktor.server.tomcat.EngineMain")
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")

}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-gson-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")  // para dar soporte a Netty
    implementation("io.ktor:ktor-server-tomcat-jvm:$ktor_version")  // para dar soporte a Tomcat
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    implementation("io.ktor:ktor-server-servlet:$ktor_version")
    // https://mvnrepository.com/artifact/io.github.lcriadof/sofia
    implementation("io.github.lcriadof:sofia:1.0")
    // html estatico
    implementation("io.ktor:ktor-server-html-builder:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    // control de sesiones
    implementation("io.ktor:ktor-server-sessions:$ktor_version")
    // autentificación
    implementation("io.ktor:ktor-server-auth:$ktor_version")
}

// Servidor
gretty {
    servletContainer = "tomcat9"  // con esta line usamos tomcat, si la quitamos usamos Jetty o Netty
    contextPath = "/"
    logbackConfigFile = "src/main/resources/logback.xml"
}



afterEvaluate {
    tasks.getByName("run") {
        dependsOn("appRun")
    }
}