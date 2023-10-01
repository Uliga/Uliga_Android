plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.google.devtools.ksp")
    id("kotlin-kapt")
    kotlin("plugin.serialization") version "1.8.21"
}

android {
    namespace = "com.uliga.data_remote"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":data"))
    implementation(Core.coreKtx)

    implementation(Kotlin.standardLibrary)
    implementation(Kotlin.serialization)

    implementation(Coroutines.android)
    implementation(Coroutines.core)

    implementation(DI.daggerHiltAndroid)
    kapt(DI.daggerHiltAndroidAnnotation)

    implementation(Remote.retrofit)
    api(Remote.retrofitScalar)
    api(Remote.retrofitConverter)
    api(Remote.retrofitAdapter)
    implementation(Remote.okHttp)
    implementation(Remote.loggingInterceptor)

    implementation(Ktor.core)
    implementation(Ktor.cio)
    implementation(Ktor.okHttp)
    implementation(Ktor.logging)
    implementation(Ktor.contentNegotiation)
    implementation(Ktor.serializationJson)
    implementation(Ktor.auth)
}