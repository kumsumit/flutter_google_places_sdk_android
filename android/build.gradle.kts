import com.android.build.api.dsl.LibraryExtension

plugins {
    id("com.android.library")
}

group = "com.msh.flutter_google_places_sdk"
version = "1.0-SNAPSHOT"

val kotlinVersion = providers.gradleProperty("kotlin_version").orElse("2.4.20").get()
val googlePlacesVersion = providers.gradleProperty("google_places_version").orElse("6.0.2").get()

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

extensions.configure<LibraryExtension> {
    namespace = "com.msh.flutter_google_places_sdk"
    compileSdk = 37

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    sourceSets {
        getByName("main").kotlin.directories += "src/main/kotlin"
        getByName("test").kotlin.directories += "src/test/kotlin"
    }

    defaultConfig {
        minSdk = 21
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    lint {
        disable += "InvalidPackage"
    }
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:$kotlinVersion"))
    implementation("com.google.android.libraries.places:places:$googlePlacesVersion")

    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:5.1.1")
    testImplementation("androidx.test:core:1.5.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
    testImplementation("org.robolectric:robolectric:4.10.3")
}
