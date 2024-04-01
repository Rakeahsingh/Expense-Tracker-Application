plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.daggerHilt)
    id("kotlin-kapt")
    id ("kotlin-parcelize")
    alias(libs.plugins.googleServices)
}

android {
    namespace = "com.rkcoding.expensetrackerapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.rkcoding.expensetrackerapplication"
        minSdk = 26
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
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // material icons
    implementation(libs.androidx.material3.icons.extended)


    // Room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.kapt.compiler)
    implementation (libs.androidx.room.paging)

    // Dagger Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // compose Navigation
    implementation(libs.androidx.navigation.compose)


    // ViewModel Lifecycle
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // firebase
    implementation(libs.firebase.bom)
    implementation(libs.firebase.auth)
    implementation(libs.play.services.auth)

    // datastore preferences
    implementation(libs.androidx.datastore.preferences)

    // Pager and Indicators - Accompanist
    implementation (libs.accompanist.pager)
    implementation (libs.accompanist.pager.indicators)
    implementation (libs.accompanist.flowlayout)


    //work
    implementation(libs.androidx.work.runtime.ktx)

    // coil
    implementation(libs.coil.compose)
}