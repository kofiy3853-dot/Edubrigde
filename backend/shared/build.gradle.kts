plugins {
    `java-library`
}

dependencies {
    api("org.springframework.boot:spring-boot-starter-validation")
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    api("org.mapstruct:mapstruct:1.6.3")
    api("io.minio:minio:8.5.12")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
    annotationProcessor("org.projectlombok:lombok")
    compileOnly("org.projectlombok:lombok")
}
