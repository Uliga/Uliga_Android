import Versions.hiltTest

object App {
    const val compileSdk = 33
    const val minSdk = 27
    const val targetSdk = 33
    const val versionCode = 7
    const val versionName = "1.0.0"
    const val buildTools = "30.0.3"
}

object View {
    //appcompat
    const val appcompat: String = "androidx.appcompat:appcompat:${Versions.appcompatVersion}"

    //material
    const val material: String = "com.google.android.material:material:${Versions.materialVersion}"

    //constraintLayout
    const val constraintLayout: String =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintVersion}"

    //swipeRefresh
    const val swipeRefreshLayout: String =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayoutVersion}"

    //glide
    const val glide: String = "com.github.bumptech.glide:glide:${Versions.glideVersion}"

    const val activity: String = "androidx.activity:activity-ktx:${Versions.activity}"

    //fragment
    const val fragmentKtx: String = "androidx.fragment:fragment-ktx:${Versions.fragmentKtxVersion}"


    //navigation
    const val navigationFragment: String =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
    const val navigationUi: String =
        "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"
    const val navigationCompose: String =
        "androidx.navigation:navigation-compose:${Versions.navigationVersion}"

    //viewPager
    const val viewPager2: String = "androidx.viewpager2:viewpager2:${Versions.viewpagerVersion}"

}


object Flow {
    const val flowbinding =
        "io.github.reactivecircus.flowbinding:flowbinding-android:${Versions.flowBindingVersion}"
}

object LifeCycle {
    const val lifecycleViewModel: String =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
    const val lifecycleSavedstate: String =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycleVersion}"
    const val lifecycleCommon: String =
        "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycleVersion}"
    const val lifecycleService: String =
        "androidx.lifecycle:lifecycle-service:${Versions.lifecycleVersion}"
    const val lifecycleProcess: String =
        "androidx.lifecycle:lifecycle-process:${Versions.lifecycleVersion}"
    const val lifecyclerRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersion}"
}

object Remote {
    const val retrofit: String = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitConverter: String =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"

    const val loggingInterceptor: String =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVersion}"
    const val retrofitAdapter: String =
        "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitVersion}"
    const val okHttp: String = "com.squareup.okhttp3:okhttp:${Versions.okHttpVersion}"
    const val retrofitScalar: String =
        "com.squareup.retrofit2:converter-scalars:${Versions.retrofitVersion}"
}

object DI {
    const val daggerHiltAndroid: String =
        "com.google.dagger:hilt-android:${Versions.daggerHiltAndroidVersion}"
    const val daggerHiltAndroidAnnotation: String =
        "com.google.dagger:hilt-android-compiler:${Versions.daggerHiltAndroidVersion}"
    const val daggerHiltComponse: String =
        "androidx.hilt:hilt-navigation-compose:${Versions.daggerHiltComposeVersion}"
}

object StartUp {
    const val startupRuntime = "androidx.startup:startup-runtime:${Versions.startupVersion}"
}

object Logger {
    const val log = "com.orhanobut:logger:${Versions.loggerVersion}"
}

object Coroutines {
    const val core: String =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
    const val android: String =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroidVersion}"
}

object Core {
    const val coreKtx: String = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
}

object Kotlin {
    const val standardLibrary: String =
        "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val serialization =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerializationJsonVersion}"
    const val dateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDateTimeVersion}"
}

object Local {
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
    const val sqlLiteJdbc = "org.xerial:sqlite-jdbc:${Versions.sqlLiteJdbcVersion}"
}

object ThirdParty {
    const val permission =
        "io.github.ParkSangGwon:tedpermission-normal:${Versions.permissionVersion}"
    const val tedActivityResult =
        "io.github.ParkSangGwon:tedonactivityresult:${Versions.tedActivityResultVersion}"
    const val calendarView = "com.github.kizitonwose:CalendarView:${Versions.calendarVersion}"
    const val flexBox = "com.google.android.flexbox:flexbox:${Versions.flexBoxVersion}"
    const val kakaoSdkUser = "com.kakao.sdk:v2-user:${Versions.kakaoSdkUser}"
    const val facebookLogin = "com.facebook.android:facebook-login:${Versions.facebookLoginVersion}"
    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    const val keyBoardObserver =
        "io.github.ParkSangGwon:tedkeyboardobserver:${Versions.keyBoardObserverVersion}"

    val orbit = "org.orbit-mvi:orbit-compose:${Versions.orbit}"
    val orbitViewModel = "org.orbit-mvi:orbit-viewmodel:${Versions.orbit}"
    val composeCalendar = "com.kizitonwose.calendar:compose:${Versions.composeCalendar}"
    val composeBottomSheetDialog = "com.holix.android:bottomsheetdialog-compose:${Versions.composeBottomSheetDialog}"
}

object Spectrum {
    const val SpectrumDefault = "com.facebook.spectrum:spectrum-default:${Versions.spectrumVersion}"
}

object WorkManager {
    const val workManager = "androidx.work:work-runtime:${Versions.workManagerVersion}"
    const val workManagerKtx = "androidx.work:work-runtime-ktx:${Versions.workManagerVersion}"
}

object Ktor {
    const val core = "io.ktor:ktor-client-core:${Versions.ktorVersion}"

    const val cio = "io.ktor:ktor-client-cio:${Versions.ktorVersion}"
    const val okHttp = "io.ktor:ktor-client-okhttp:${Versions.ktorVersion}"

    const val logging = "io.ktor:ktor-client-logging:${Versions.ktorVersion}"

    const val contentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktorVersion}"
    const val serializationJson = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktorVersion}"

    const val auth = "io.ktor:ktor-client-auth:${Versions.ktorVersion}"
}

object DataStore {
    const val preferences = "androidx.datastore:datastore-preferences:${Versions.dataStoreVersion}"
    const val preferencesCore =
        "androidx.datastore:datastore-preferences-core:${Versions.dataStoreVersion}"
}

object Advertisement {
    //모멘토
    const val momento = "com.github.momento-ads:momento-android-sdk:${Versions.momentoVersion}"
}

object Compose {
    val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivityVersion}"
    val composeUi = "androidx.compose.ui:ui:${Versions.composeUiVersion}"
    val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.composeUiVersion}"
    val composeFoundation = "androidx.compose.foundation:foundation:1.4.3"
    val composeHiltNavigation = "androidx.hilt:hilt-navigation-compose:1.0.0"
    val composeMaterial = "androidx.compose.material:material:1.4.3"
    val composeMaterial3 = "androidx.compose.material3:material3:1.1.0"
    val composeUiPreview ="androidx.compose.ui:ui-tooling-preview:${Versions.composeUiVersion}"
    val composeConstraintLayout = "androidx.constraintlayout:constraintlayout-compose:${Versions.composeConstraintLayoutVersion}"
}

object Billing {
    val billingClient = "com.android.billingclient:billing:${Versions.billingVersion}"
    val guavaAvoidConflict = "com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava"
    val guava = "com.google.guava:guava:24.1-jre"
}

object Testing {
    val composeTestRunner = "androidx.test:runner:${Versions.composeTestRunner}"
    val composeTestUiManifest = "androidx.compose.ui:ui-test-manifest:${Versions.composeTestUi}"
    val composeJunit4 = "androidx.compose.ui:ui-test-junit4-android:${Versions.composeTestUi}"
    val hiltTest = "com.google.dagger:hilt-android-testing:${Versions.hiltTest}"
    val hiltTestCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltTest}"
}

object Google {
    val googleServicesAuth = "com.google.android.gms:play-services-auth:${Versions.googleServiceAuth}"
}

object Lifecycles {
    val composeRuntime = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycleCompose}"
    val composeNaviagtion  = "androidx.navigation:navigation-compose:${Versions.lifecycleComposeNavigation}"
    val LifecycleProcess = "androidx.lifecycle:lifecycle-process:${Versions.lifecycleCompose}"
    val LIfecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleCompose}"
}

object Domain {
    const val testCore = "androidx.test:core:1.4.0"
    const val junit = "junit:junit:4.13.2"
    const val googleTruth = "com.google.truth:truth:1.1.3"
}

object Versions {
    const val gradleVersion = "7.4.2"
    const val kotlinVersion = "1.8.10"
    const val kotlinxDateTimeVersion = "0.4.0"
    const val kotlinxSerializationJsonVersion = "1.4.1"
    const val retrofitVersion = "2.9.0"
    const val fragmentKtxVersion = "1.5.2"
    const val okHttpVersion = "4.8.0"
    const val coreKtxVersion = "1.3.0"
    const val navigationVersion = "2.4.2"
    const val viewpagerVersion = "1.0.0"
    const val appcompatVersion = "1.6.1"
    const val constraintVersion = "1.1.3"
    const val roomVersion = "2.5.0"
    const val sqlLiteJdbcVersion = "3.36.0"
    const val dataStoreVersion = "1.0.0"
    const val materialVersion = "1.4.0"
    const val swipeRefreshLayoutVersion = "1.1.0"
    const val glideVersion = "4.11.0"
    const val lifecycleVersion = "2.5.1"
    const val daggerHiltAndroidVersion = "2.45"
    const val daggerHiltComposeVersion = "1.0.0"
    const val coroutinesVersion = "1.6.4"
    const val coroutinesAndroidVersion = "1.6.4"
    const val calendarVersion = "1.0.3"
    const val permissionVersion = "3.3.0"
    const val flexBoxVersion = "3.0.0"
    const val kakaoSdkUser = "2.17.0"
    const val startupVersion = "1.1.1"
    const val loggerVersion = "2.2.0"
    const val spectrumVersion = "1.3.0"
    const val gsonVersion = "2.9.0"
    const val flowBindingVersion = "1.2.0"
    const val workManagerVersion = "2.7.0"
    const val facebookLoginVersion = "latest.release"
    const val keyBoardObserverVersion = "1.0.1"
    const val tedActivityResultVersion = "1.0.10"
    const val ktorVersion = "2.3.0"
    const val momentoVersion = "1.0.4"
    const val activity = "1.6.1"

    const val composeConstraintLayoutVersion = "1.0.1"
    const val composeActivityVersion = "1.7.0"
    const val composeUiVersion = "1.4.3"
    const val composeMaterial3Version = "1.0.1"

    const val billingVersion = "5.0.0"

    const val composeTestRunner = "1.4.0"
    const val composeTestUi = "1.5.4"

    const val hiltTest = "2.37.0"

    const val orbit = "6.1.0"

    const val composeCalendar = "2.4.0"
    const val composeBottomSheetDialog = "1.3.1"

    const val googleServiceAuth = "20.7.0"
    const val lifecycleComposeNavigation = "2.5.3"
    const val lifecycleCompose = "2.6.1"
}
