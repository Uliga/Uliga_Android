plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
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
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.5"
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

    implementation(Compose.composeActivity)
    implementation(Compose.composeUi)
    implementation(Compose.composeTooling)
    implementation(Compose.composeFoundation)
    implementation(Compose.composeHiltNavigation)
    implementation(Compose.composeMaterial)
    implementation(Compose.composeMaterial3)
    implementation(Compose.composeUiPreview)
    implementation(Compose.composeConstraintLayout)


    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")
    implementation("androidx.lifecycle:lifecycle-process:2.6.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation( "androidx.navigation:navigation-compose:2.5.3")

    implementation(View.navigationCompose)

    implementation("co.yml:ycharts:2.1.0")
}