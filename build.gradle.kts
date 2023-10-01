// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.google.devtools.ksp") version "1.8.20-1.0.10" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}

buildscript {

    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }

    dependencies {
        classpath("com.google.gms:google-services:4.4.0")
        classpath("com.android.tools.build:gradle:${Versions.gradleVersion}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.daggerHiltAndroidVersion}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationVersion}")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://devrepo.kakao.com/nexus/content/groups/public/")
    }
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
