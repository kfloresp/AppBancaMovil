plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-parcelize")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.rgk.appbancamovil"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.rgk.appbancamovil"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    // Fragment
    implementation ("androidx.fragment:fragment-ktx:1.5.5")

    // Activity
    implementation ("androidx.activity:activity-ktx:1.6.1")

    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")

    // LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")

    //Kotlin core y Corrutinas
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")
    implementation ("androidx.core:core-ktx:1.9.0")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")

    //Daggerhilt
    implementation ("com.google.dagger:hilt-android:2.47")
    kapt ("com.google.dagger:hilt-android-compiler:2.47")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.11.0")

    //Toasty
    implementation ("com.github.GrenderG:Toasty:1.5.2")

    //Picasso
    implementation ("com.github.bumptech.glide:glide:4.15.1")

    //Libreria shimmer - efectos carga
    implementation ("com.facebook.shimmer:shimmer:0.5.0")

    //Swipe para actualizar recycler
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}