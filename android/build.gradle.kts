import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.android.build.api.dsl.LibraryExtension

group = "com.msh.flutter_google_places_sdk"
version = "1.0-SNAPSHOT"

val kotlinVersion = providers.gradleProperty("kotlin_version").get()
val googlePlacesVersion = providers.gradleProperty("google_places_version").get()

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:9.4.1")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

apply(plugin = "com.android.library")

extensions.configure<LibraryExtension> {
    namespace = "com.msh.flutter_google_places_sdk"
    compileSdk = 37

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    sourceSets {
        getByName("main").java.srcDir("src/main/kotlin")
        getByName("test").java.srcDir("src/test/kotlin")
    }

    defaultConfig {
        minSdk = 21
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    lint {
        disable += "InvalidPackage"
    }
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
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
