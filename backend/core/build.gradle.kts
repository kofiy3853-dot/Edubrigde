plugins {
    id("org.springframework.boot")
}

dependencies {
    implementation(project(":backend:shared"))
    implementation(project(":backend:identity"))
    implementation(project(":backend:academic"))
    implementation(project(":backend:career"))
    implementation(project(":backend:financial"))
    implementation(project(":backend:institution"))
    implementation(project(":backend:communication"))
    implementation(project(":backend:analytics"))
    implementation(project(":backend:ai"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-postgresql")
    implementation("org.postgresql:postgresql")
    runtimeOnly("org.postgresql:postgresql")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    mainClass.set("com.edubridge.core.EdubridgeApplication")
    archiveFileName.set("edubridge.jar")
}

tasks.named<org.gradle.jvm.tasks.Jar>("jar") {
    enabled = false
}
