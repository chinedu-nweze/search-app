plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.plugin.serialization)
}

android {
    namespace = "com.search.app"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.search.app"
        minSdk = 30
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.material.icons)

    implementation(platform(libs.io.insert.koin.koin.bom))
    implementation(libs.io.insert.koin.koin.core)
    implementation(libs.io.insert.koin.koin.androidx.compose)
    implementation(libs.io.insert.koin.koin.android)
    implementation(libs.io.insert.koin.koin.androidx.startup)

    implementation(platform(libs.com.squareup.okhttp3.okhttp.bom))
    implementation(libs.com.squareup.okhttp3.logging.interceptor)
    implementation(libs.com.squareup.retrofit2.converter.kotlinx.serialization)
    implementation(libs.org.jetbrains.kotlinx.kotlinx.serialization.json )
    implementation(libs.com.squareup.retrofit2.retrofit)

    implementation(libs.com.jakewharton.timber.timber)

    implementation(libs.io.coil.kt.coil3.coil.compose)
    //implementation(libs.io.coil.kt.coil3.coil.network )
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.0.4") // Only available on Android/JVM.
    implementation("io.coil-kt.coil3:coil-network-ktor2:3.0.4")
    implementation("io.coil-kt.coil3:coil-network-ktor3:3.0.4")

    implementation(libs.androidx.navigation.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}