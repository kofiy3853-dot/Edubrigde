plugins {
    `java-library`
}

dependencies {
    implementation(project(":backend:shared"))
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
}
