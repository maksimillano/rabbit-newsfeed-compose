import java.text.SimpleDateFormat
import java.util.*

plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("org.jetbrains.compose")
}

kotlin {
    @Suppress("OPT_IN_USAGE")
    targetHierarchy.default()
    androidTarget()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))

                implementation("com.arkivanov.decompose:decompose:2.1.4")
                implementation("com.arkivanov.decompose:extensions-compose-jetbrains:2.1.4-compose-experimental")

                implementation(libs.koin.core)
                implementation(libs.koin.android)
            }
        }
    }
}

android {
    val packageName = project.property("maksimillano.package").toString()

    compileSdk = 34
    namespace = "$packageName.android"

    defaultConfig {
        applicationId = "$packageName.android"
        minSdk = 26
        targetSdk = 34
        versionCode = project.property("maksimillano.android.versionCode").toString().toInt()
        versionName = project.property("maksimillano.appVersion").toString()
    }

    buildTypes {
        getByName("debug") {
            applicationVariants.all {
                outputs.all {
                    val outputImpl = this as com.android.build.gradle.internal.api.BaseVariantOutputImpl
                    outputImpl.outputFileName = "${gitBranchName()} (${getCurrentDate()}).apk"
                }
            }
        }

        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
                "kmm-rules.pro"
            )

            // TODO: https://github.com/users/maxshengelia/projects/4/views/5?pane=issue&itemId=57486667
            lint {
                checkReleaseBuilds = false
                abortOnError = false
            }

            applicationVariants.all {
                outputs.all {
                    val outputImpl = this as com.android.build.gradle.internal.api.BaseVariantOutputImpl
                    outputImpl.outputFileName = "maksimillano-release.apk"
                }
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
    }
}

fun gitBranchName(): String {
    val defaultName = "test-apk"
    val builder = ProcessBuilder()
        .directory(File(System.getProperty("user.dir")))
        .command("git", "rev-parse", "--abbrev-ref", "HEAD")

    return try {
        val process = builder.start()
        val bufferedReader = process.inputStream.bufferedReader()
        bufferedReader.readLine()
    } catch (throwable: Throwable) {
        println(throwable.printStackTrace())
        defaultName
    }
}

fun getCurrentDate(): String {
    val currentDate = Date()
    return SimpleDateFormat("dd.MM.yyyy").format(currentDate)
}