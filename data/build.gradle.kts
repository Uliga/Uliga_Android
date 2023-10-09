plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.google.devtools.ksp")
    id("kotlin-kapt")
    kotlin("plugin.serialization") version "1.8.21"
}

android {
    namespace = "com.uliga.data"
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
    implementation(project(":domain"))

    implementation(Core.coreKtx)

    implementation(Coroutines.core)
    implementation(Coroutines.android)
    implementation(Kotlin.serialization)

    implementation(ThirdParty.gson)

    implementation(DI.daggerHiltAndroid)
    kapt(DI.daggerHiltAndroidAnnotation)
}