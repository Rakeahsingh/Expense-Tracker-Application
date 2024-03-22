plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id ("kotlin-parcelize")
    id("com.google.gms.google-services")
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
    implementation("androidx.compose.material:material-icons-extended:1.6.3")


    // Material 3
    implementation("androidx.compose.material3:material3:1.2.1")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.room:room-paging:2.6.1")

    // Dragger Hilt
    implementation("com.google.dagger:hilt-android:2.49")
    kapt("com.google.dagger:hilt-android-compiler:2.49")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // compose Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")


    // ViewModel Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.4"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth:22.3.1")

    // datastore preferences
    //noinspection UseTomlInstead
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Pager and Indicators - Accompanist
    //noinspection UseTomlInstead
    implementation ("com.google.accompanist:accompanist-pager:0.19.0")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.19.0")
    implementation ("com.google.accompanist:accompanist-flowlayout:0.19.0")

    // Compose Constraint Layout
    //noinspection UseTomlInstead
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    //work
    //noinspection UseTomlInstead
    implementation("androidx.work:work-runtime-ktx:2.9.0")
}