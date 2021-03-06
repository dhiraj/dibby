import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import java.util.Properties

plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("dagger.hilt.android.plugin")
    kotlin("kapt")
}
val properties = gradleLocalProperties(rootDir)
val tmdbAPIKey = properties.getProperty("tmdbApiKey")

android {
    compileSdkVersion(31)

    defaultConfig {
        applicationId = ("com.traversient.dibby")
        minSdk = 24
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String","TMDB_API_KEY",tmdbAPIKey)

        testInstrumentationRunner  = "androidx.test.runner.AndroidJUnitRunner"
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true",
                    "room.expandProjection" to "true"
                )
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility (JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation ("androidx.core:core-ktx:1.7.0")
    implementation ("androidx.appcompat:appcompat:1.4.1")
    implementation ("com.google.android.material:material:1.5.0")
    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.4.1")
    implementation ("androidx.navigation:navigation-ui-ktx:2.4.1")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")


    //Room
    val roomVersion = "2.4.2"
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion") // optional - Kotlin Extensions and Coroutines support for Room
    testImplementation("androidx.room:room-testing:$roomVersion")// optional - Test helpers
    implementation("androidx.room:room-paging:2.5.0-alpha01")// optional - Paging 3 Integration

    //Flipper
    debugImplementation("com.facebook.flipper:flipper:0.138.0")
    debugImplementation("com.facebook.soloader:soloader:0.10.3")
    releaseImplementation("com.facebook.flipper:flipper-noop:0.138.0")
    debugImplementation ("com.facebook.flipper:flipper-leakcanary2-plugin:0.138.0")
    debugImplementation ("com.squareup.leakcanary:leakcanary-android:2.8.1")
    debugImplementation("com.facebook.flipper:flipper-network-plugin:0.138.0")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.41")
    kapt("com.google.dagger:hilt-compiler:2.41")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.41")
    kaptAndroidTest("com.google.dagger:hilt-compiler:2.41")
    testImplementation("com.google.dagger:hilt-android-testing:2.41")
    kaptTest("com.google.dagger:hilt-compiler:2.41")


    implementation("com.airbnb.android:lottie:5.0.3")
    implementation("io.coil-kt:coil:2.0.0-rc01")
    implementation("com.jakewharton.timber:timber:5.0.1")

}

kapt{
    correctErrorTypes = true
}

hilt{
    enableAggregatingTask = true
}
