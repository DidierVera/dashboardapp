import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    kotlin("plugin.serialization") version "1.9.0" // Add this line
    kotlin("kapt")

}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
        }
        binaries.executable()
    }
    
    sourceSets {
        configurations.all {
            resolutionStrategy.force("org.jetbrains.kotlin:kotlin-stdlib:2.0.21")
        }
        wasmJsMain.dependencies {
            //Serialization
            implementation(libs.serialization.json)
            //implementation("io.insert-koin:koin-core:3.2.0")
        }

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            //signal R
            implementation(libs.signalr)
// Retrofit for network requests
            implementation (libs.retrofit)
            //implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
            implementation (libs.logging.interceptor)

            //Serialization
            implementation(libs.serialization.json)
            //nano http server hub
            implementation (libs.nanohttpd)

            //image
            implementation (libs.coil)
            implementation (libs.coil.compose)

        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.koin.compose)
            implementation(libs.koin.composeVM)

            //Serialization
            implementation(libs.serialization.core)
            implementation(libs.serialization.json)

            //Koin
            implementation(libs.koin.core)
        }
    }
}

android {
    namespace = "com.cameparkare.dashboardapp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.cameparkare.dashboardapp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    dependencies {
        // Room database
        val roomVersion = "2.5.2"
        implementation("androidx.room:room-runtime:$roomVersion")
        implementation("androidx.room:room-ktx:$roomVersion")
        //kapt { "androidx.room:room-compiler:$roomVersion" }

    }
}

dependencies {
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.media3.common.ktx)
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.ui)
    debugImplementation(compose.uiTooling)
}

