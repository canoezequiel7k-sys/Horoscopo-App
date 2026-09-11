plugins {
    alias(libs.plugins.android.application)
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.cursokotlin.horoscapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.cursokotlin.horoscapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        //Cuando este aqui tengo unos parametros o datos
        getByName("release") {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            resValue("string", "ariname", "HoroscApp")
            buildConfigField("String", "BASE_URL", "\"https://newastro.vercel.app/\"")
        }
        getByName("debug"){
            isDebuggable = true
            resValue("string", "ariname", "[DEBUG]HoroscApp")
            buildConfigField("String", "BASE_URL", "\"https://newastro-debug.vercel.app/\"")
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
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    //navComponent
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //DagerHilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-compiler:2.51.1")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //Interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    //CameraX
    val cameraxVersion = "1.3.4"
    implementation("androidx.camera:camera-core:$cameraxVersion")
    implementation("androidx.camera:camera-camera2:$cameraxVersion")
    implementation("androidx.camera:camera-lifecycle:$cameraxVersion")
    implementation("androidx.camera:camera-view:$cameraxVersion")
    implementation("androidx.camera:camera-extensions:$cameraxVersion")


    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)

    //Librerias de TESTING




    //-----Libreria que solo funciona en el directorio test
    testImplementation(libs.junit)
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.4.2")
    //Libreria que nos permite mockear
    testImplementation("io.mockk:mockk:1.12.3")

    //-----Libreria que solo funciona en el directorio androidTest
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}