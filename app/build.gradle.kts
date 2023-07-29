plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs")
    id("kotlin-parcelize")
    kotlin("plugin.serialization") version "1.8.21"
}

android {
    compileSdk = App.compileSdk
    buildToolsVersion = App.buildTools

    defaultConfig {
        applicationId = "com.uliga.app"
        minSdk = App.minSdk
        targetSdk = App.targetSdk
        versionCode = App.versionCode
        versionName = App.versionName

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(Core.coreKtx)

    implementation(View.appcompat)
    implementation(View.material)
    implementation(View.constraintLayout)
    implementation(View.navigationFragment)
    implementation(View.navigationUi)
    implementation(View.fragmentKtx)

    implementation(DI.daggerHiltAndroid)
    implementation(DI.daggerHiltComponse)
    kapt(DI.daggerHiltAndroidAnnotation)

    implementation(Coroutines.core)
    implementation(Coroutines.android)

    implementation(Flow.flowbinding)
    implementation(View.glide)




}