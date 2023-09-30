plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.uliga.chart"
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.5"
    }
}

dependencies {
    implementation(Core.coreKtx)

    implementation(Coroutines.core)
    implementation(Coroutines.android)

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
}