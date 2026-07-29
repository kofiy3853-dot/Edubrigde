plugins {
    `java-library`
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
    implementation(project(":backend:integration"))

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("com.h2database:h2")
}
