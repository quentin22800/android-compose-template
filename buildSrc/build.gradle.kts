plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(kotlin("gradle-plugin","1.7.10"))
    implementation("com.android.tools.build:gradle:7.4.1")
    implementation("com.squareup:javapoet:1.13.0") // FIXME https://github.com/google/dagger/issues/3068
}