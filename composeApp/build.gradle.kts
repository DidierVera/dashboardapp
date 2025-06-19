import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.ksp)
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    kotlin("plugin.serialization") version "2.1.0"
}

kotlin {
    androidTarget {
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
            implementation(libs.kotlin.stdlib.wasm.js)
            //Serialization
            implementation(libs.serialization.json)
            implementation(libs.koin.core)
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
            implementation (libs.logging.interceptor)

            //Serialization
            implementation(libs.serialization.json)
            implementation(libs.gson)

            //nano http server hub
            implementation (libs.nanohttpd)

            //image
            implementation (libs.coil)
            implementation (libs.coil.compose)
            implementation (libs.ftpserver.core)
            implementation (libs.mina.core)
            //Room database
            implementation (libs.room.ktx)
            implementation (libs.room.runtime)

            //firebase
            implementation(project.dependencies.platform("com.google.firebase:firebase-bom:33.12.0"))
            implementation("com.google.firebase:firebase-analytics")
            implementation(libs.firebase.crashlytics.ktx)
            implementation(libs.firebase.analytics.ktx)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.components.resources)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            //Serialization
            implementation(libs.serialization.core)
            implementation(libs.serialization.json)

            //Koin
            implementation(libs.koin.compose)
            implementation(libs.koin.composeVM)
            implementation(libs.koin.core)
        }
    }
}

android {
    namespace = "com.came.parkare.dashboardapp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.came.parkare.dashboardapp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 49
        versionName = "2.0.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/DEPENDENCIES"
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    ksp {
        arg("room.schemaLocation", "$projectDir/schemas")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    dependencies {
        // KSP dependency for Room
        ksp("androidx.room:room-compiler:2.5.2")
    }
}

dependencies {
    add("kspAndroid", "com.google.devtools.ksp:symbol-processing-api:1.9.0-1.0.13")
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.media3.common.ktx)
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.ui)
    debugImplementation(compose.uiTooling)

}

