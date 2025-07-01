plugins {
    id("java")
    id("com.github.gmazzo.buildconfig") version "5.6.5"
    id("maven-publish")
}

val apiVersion: String by project
val apiVersionString = apiVersion as? String

group = "com.dmoser.codyssey.bragi.snapcast"
version = apiVersion

java {
    withJavadocJar()
    withSourcesJar()
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
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
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.1")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.15.1")
    implementation("org.java-websocket:Java-WebSocket:1.5.4")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    implementation("org.apache.logging.log4j:log4j-api:2.22.1")
    runtimeOnly("org.apache.logging.log4j:log4j-core:2.22.1")
    implementation("org.slf4j:slf4j-log4j12:2.0.17")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.named<Javadoc>("javadoc") {
    dependsOn("addJavadocToBuildConfig") // Ensure Javadoc is added before publishing
    isFailOnError = true
}

buildConfig {
    packageName("com.dmoser.codyssey.bragi.snapcast.api")
    className("Constants")
    documentation.set("Automatically generated Class with runtime constants defined in the gradle.properties file.")
    buildConfigField("String", "API_VERSION", apiVersionString)
}
// Generate doc for Constants file.
tasks.register("addJavadocToBuildConfig") {
    group = "documentation" // This groups the task under the 'documentation' section
    dependsOn("generateBuildConfig")
    doLast {
        val constantsFile = file(
            "build/generated/sources/buildConfig/main/com/dmoser/codyssey/bragi/snapcast/api/Constants" +
                    ".java"
        )
        println(constantsFile.path)
        if (constantsFile.exists()) {
            println("fileExists")
            // Read the file
            var content = constantsFile.readText()

            // Add Javadoc comments to fields
            content = content.replace(
                "public static",
                "/** Automatically generated Value. */\n  public static"
            )

            println(content)

            // Write the modified content back to the file
            constantsFile.writeText(content)
        }
    }
}

tasks.named("publish") {
    dependsOn("test", "javadoc")
}
