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

        configurations.all {
            resolutionStrategy {
                force("androidx.emoji2:emoji2-views-helper:1.3.0")
                force("androidx.emoji2:emoji2:1.3.0")
            }
        }

        applicationId = "com.uliga.app"
        minSdk = App.minSdk
        targetSdk = App.targetSdk
        versionCode = App.versionCode
        versionName = App.versionName

        testInstrumentationRunner = "com.uliga.app.HiltTestRunner"
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

    testOptions {
        animationsDisabled = true
    }
}

dependencies {
    implementation(project(":chart"))

    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":data-remote"))
    implementation(project(":data-local"))

    implementation(Core.coreKtx)
    implementation(Flow.flowbinding)

    // View
    implementation(View.appcompat)
    implementation(View.material)
    implementation(View.constraintLayout)
    implementation(View.navigationFragment)
    implementation(View.navigationUi)
    implementation(View.fragmentKtx)
    implementation(View.glide)
    implementation(View.navigationCompose)

    // DI
    implementation(DI.daggerHiltAndroid)
    implementation(DI.daggerHiltComponse)

    kapt(DI.daggerHiltAndroidAnnotation)

    // Coroutine
    implementation(Coroutines.core)
    implementation(Coroutines.android)

    // Compose
    implementation(Compose.composeActivity)
    implementation(Compose.composeUi)
    implementation(Compose.composeTooling)
    implementation(Compose.composeFoundation)
    implementation(Compose.composeHiltNavigation)
    implementation(Compose.composeMaterial)
    implementation(Compose.composeMaterial3)
    implementation(Compose.composeUiPreview)
    implementation(Compose.composeConstraintLayout)

    // Lifecycle
    implementation(Lifecycles.composeRuntime)
    implementation(Lifecycles.composeNaviagtion)
    implementation(Lifecycles.LifecycleProcess)
    implementation(Lifecycles.LIfecycleRuntime)

    // ThirdParty
    implementation(ThirdParty.composeCalendar)
    implementation(ThirdParty.orbit)
    implementation(ThirdParty.orbitViewModel)
    implementation(ThirdParty.permission)
    implementation(ThirdParty.kakaoSdkUser)
    implementation(ThirdParty.composeBottomSheetDialog)

    // Google
    implementation(Google.googleServicesAuth)

    // Instrumentation tests
    androidTestImplementation(Testing.composeTestRunner)
    androidTestImplementation(Testing.composeTestUiManifest)
    implementation(Testing.composeJunit4)
    androidTestImplementation(Testing.hiltTest)
    kaptAndroidTest(Testing.hiltTestCompiler)

}