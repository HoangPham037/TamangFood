plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.tamangfood"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tamangfood"
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
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation("com.google.firebase:firebase-firestore-ktx:24.9.0")

    implementation("androidx.room:room-runtime:2.3.0")
    kapt ("androidx.room:room-compiler:2.3.0")

    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt ("com.google.dagger:hilt-android-compiler:2.48.1")

    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("androidx.fragment:fragment-ktx:1.6.1")

    //navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:2.3.1")
    implementation ("androidx.navigation:navigation-ui-ktx:2.3.1")

    //page indicator view
    implementation("com.tbuonomo:dotsindicator:5.0")

    //firebase
    implementation ("com.google.firebase:firebase-auth-ktx:20.0.1")
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation ("me.relex:circleindicator:2.1.6")
    implementation("com.google.firebase:firebase-firestore")
    //glide
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    kapt ("com.github.bumptech.glide:compiler:4.12.0")

    //timber log
    implementation("com.jakewharton.timber:timber:4.7.1")

    //splash
    implementation("androidx.core:core-splashscreen:1.0.0")
}
kapt {
    correctErrorTypes = true
}