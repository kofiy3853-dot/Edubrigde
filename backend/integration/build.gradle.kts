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
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
}
