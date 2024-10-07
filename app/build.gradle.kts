plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.dagger.hilt)
    id("kotlin-kapt")
}

android {
    namespace = "com.nirali"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.nirali"
        minSdk = 34
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    androidTestImplementation ("androidx.test:rules:1.4.0")
    androidTestImplementation(libs.androidx.test.rules)
    // Test rules and transitive dependencies:
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.1.1")
// Needed for createComposeRule(), but not for createAndroidComposeRule<YourActivity>():
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.1.1")
    testImplementation( "org.mockito:mockito-core:4.3.1")
    testImplementation ("org.mockito:mockito-inline:4.3.1")
    testImplementation( "app.cash.turbine:turbine:0.12.1")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.logging.interceptor)

    implementation(libs.coil.compose)

    //Koin
    implementation("io.insert-koin:koin-android:3.4.3")
    implementation(libs.koin.androidx.compose)

    //lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.1")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.1")
    implementation("androidx.activity:activity-ktx:1.9.0")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.8")

    //navigation
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    //constraintlayout
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.0-beta02")


    // Room components
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-testing:2.6.1")

    // -- Compose
    implementation("androidx.compose.material:material-icons-extended:1.6.7")

    implementation("com.google.android.gms:play-services-auth:20.4.1")

    implementation("androidx.datastore:datastore-preferences:1.1.1")
    implementation("androidx.datastore:datastore:1.1.1")

// Add the Firebase SDK for Authentication
    implementation("com.google.firebase:firebase-auth-ktx:23.0.0")
//Circular Imageview
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")
    //work manager
    implementation(libs.workManager)

    // dagger hilt
    implementation(libs.google.dagger.hilt)
    kapt(libs.google.dagger.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
//    implementation("com.google.dagger:hilt-android:2.51.1")
//    ksp("com.google.dagger:dagger-compiler:2.51.1")
//    ksp("com.google.dagger:hilt-compiler:2.51.1")
    implementation("eu.bambooapps:compose-material3-pullrefresh:1.0.0")

    implementation ("androidx.datastore:datastore-preferences:1.0.0")

    implementation ("androidx.security:security-crypto:1.1.0-alpha03")

    // pagingVersion = 3.2.0-alpha06
    implementation ("androidx.paging:paging-runtime: 3.2.0-alpha06")
    implementation ("androidx.paging:paging-compose:1.0.0-alpha20")


}