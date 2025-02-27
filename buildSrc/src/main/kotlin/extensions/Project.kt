package extensions

import com.android.build.gradle.LibraryExtension
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.internal.plugins.AppPlugin
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

fun Project.compose() {
    plugins.apply("kotlin-kapt")

    if (plugins.hasPlugin(AppPlugin::class.java)) {
        androidApplication {
            buildFeatures {
                compose = true
            }
            composeOptions {
                kotlinCompilerExtensionVersion = Versions.Compose.compiler
            }
        }
    } else {
        androidLibrary {
            buildFeatures {
                compose = true
            }
            composeOptions {
                kotlinCompilerExtensionVersion = Versions.Compose.compiler
            }
        }
    }

    dependencies {
        add("implementation", "androidx.compose.material:material:${Versions.Compose.compose}")
        add("implementation", "androidx.compose.ui:ui:${Versions.Compose.compose}")
        add("implementation", "androidx.compose.ui:ui-tooling-preview:${Versions.Compose.uiTooling}")
        add("implementation", "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.AndroidX.lifecycle}")

        add("debugImplementation", "androidx.compose.ui:ui-tooling:${Versions.Compose.uiTooling}")

        add("androidTestImplementation", "androidx.compose.ui:ui-test-junit4:${Versions.Compose.compose}")
    }
}

fun Project.room() {
    plugins.apply("kotlin-kapt")

    dependencies {
        add("implementation", "androidx.room:room-runtime:${Versions.AndroidX.room}")
        add("implementation", "androidx.room:room-ktx:${Versions.AndroidX.room}")
        add("annotationProcessor", "androidx.room:room-compiler:${Versions.AndroidX.room}")
        add("kapt", "androidx.room:room-compiler:${Versions.AndroidX.room}")
    }
}

fun Project.hilt() {
    plugins.apply("kotlin-kapt")
    if (plugins.hasPlugin(AppPlugin::class.java)) {
        plugins.apply("dagger.hilt.android.plugin")
    }

    // Allow references to generated code
    kapt {
        correctErrorTypes = true
    }

    dependencies {
        add("implementation", "com.google.dagger:hilt-android:${Versions.hilt}")
        add("kapt", "com.google.dagger:hilt-android-compiler:${Versions.hilt}")
        // FIXME - not for all, only if compose
        add("implementation", "androidx.hilt:hilt-navigation-compose:1.0.0")
    }
}

///////////////////////////////////////////////////////////////////////////
// Private
///////////////////////////////////////////////////////////////////////////

private fun Project.androidLibrary(configure: Action<LibraryExtension>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("android", configure)

private fun Project.androidApplication(configure: Action<ApplicationExtension>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("android", configure)

private fun Project.kapt(configure: Action<KaptExtension>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("kapt", configure)