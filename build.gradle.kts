plugins {
    id("java")
    id("maven-publish")
}

group = "com.dmoser.codyssey"
version = "1.0.0"

java {
    withJavadocJar()
    withSourcesJar()
}

repositories {
    mavenCentral()
    maven {
        isAllowInsecureProtocol = true
        url = uri(findProperty("repositoryUrl") as String)
    }
}

publishing {
    publications {
        create<MavenPublication>(project.name) {
            artifactId = project.name
            from(components["java"])
        }
    }
    repositories {
        maven {
            isAllowInsecureProtocol = true
            url = uri(findProperty("repositoryUrl") as String)
            credentials {
                username = findProperty("repositoryUsername") as String
                password = findProperty("repositoryPassword") as String
            }
        }
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}