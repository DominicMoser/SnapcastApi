plugins {
    id("java")
    // Plugin to generate a class containing constants defined in the build system (e.g., from gradle.properties)
    id("com.github.gmazzo.buildconfig") version "5.6.5"
    id("maven-publish")
}

// Retrieve project properties from gradle.properties
val projectVersion: String by project
val groupName: String by project

group = groupName
version = projectVersion

java {
    withJavadocJar()
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.1")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.15.1")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.15.1") // or latest version

    implementation("org.java-websocket:Java-WebSocket:1.5.4")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    implementation("org.apache.logging.log4j:log4j-api:2.22.1")
    runtimeOnly("org.apache.logging.log4j:log4j-core:2.22.1")
    implementation("org.slf4j:slf4j-log4j12:2.0.17")

}

publishing {
    publications {
        create<MavenPublication>(project.name) {
            artifactId = project.name
            from(components["java"])
        }
    }
}

// Configure the constants that should be created
buildConfig {
    packageName(project.group.toString().lowercase() + "." + project.name.lowercase())
    className("Constants")
    documentation.set("Automatically generated class with runtime constants defined in the gradle.properties file.")
    buildConfigField("String", "API_VERSION", projectVersion as? String)
}

tasks.test {
    useJUnitPlatform()
}

tasks.javadoc {
    // First, generate some comments for the generated Constants class
    dependsOn("addJavadocToBuildConfig")
    // Fail if Javadoc is incomplete
    isFailOnError = true
    (options as StandardJavadocDocletOptions).apply {
        addBooleanOption("Xwerror", true)
        addStringOption("Xdoclint:all")
    }
}

tasks.publish {
    // Only publish when tests run without errors and Javadocs are generated
    dependsOn("test", "javadoc")
}

tasks.register("addJavadocToBuildConfig") {
    // Groups the task under the 'documentation' section
    group = "documentation"
    dependsOn("generateBuildConfig")
    doLast {
        val constantsFile = file(
            "build/generated/sources/buildConfig/main/" +
                    project.group.toString().lowercase().replace(".", "/") +
                    "/" +
                    project.name.lowercase() +
                    "/" +
                    "Constants.java"
        )
        println(constantsFile.path)
        if (constantsFile.exists()) {
            // Read the file
            var content = constantsFile.readText()

            // Add Javadoc comments to fields
            content = content.replace(
                "public static",
                "/** Automatically generated value. */\n  public static"
            )
            // Write the modified content back to the file
            constantsFile.writeText(content)
        }
    }
}