import com.codingfeline.buildkonfig.compiler.FieldSpec
import com.cyphercove.icondivvy.IconDivvyPlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")

    kotlin("plugin.serialization") version "2.0.0-Beta1"
    id("com.codingfeline.buildkonfig")
    id("dev.icerock.mobile.multiplatform-resources")
}

buildscript{
    repositories{
    }
    dependencies {
        classpath(
            files("../tools/gradle/divvy-plugin.jar")
        )
    }
}
apply<IconDivvyPlugin>()


version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
}

kotlin {
    androidTarget()

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries {
            framework {
                baseName = "shared"
                isStatic = true
            }
        }
    }

    js(IR) {
        browser()
    }

    sourceSets {
        val coroutinesVersion = "1.7.3"
        val dateTimeVersion = "0.5.0"
        val mokoResourcesVersion = extra["moko.resources.version"] as String


        val commonMain by getting {
            dependencies {
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                // Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

                // Image loader + LRU cache
                implementation("media.kamel:kamel-image:0.9.0")

                // Settings (Preferences)
                implementation("com.russhwolf:multiplatform-settings:1.1.0")

                // Moko resources
                api("dev.icerock.moko:resources:${mokoResourcesVersion}")
                api("dev.icerock.moko:resources-compose:${mokoResourcesVersion}")

                // DateTime
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion")
                // Logging
                implementation("co.touchlab:kermit:2.0.2")
                // Serialization
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)

            dependencies {
                api("androidx.activity:activity-compose:1.7.2")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.10.1")

                // Compose Preview
                implementation("androidx.compose.ui:ui-tooling-preview:1.5.4")
            }
        }

        val iosMain by creating {
            dependsOn(commonMain)
        }
        val iosX64Main by getting {
            dependsOn(iosMain)
        }
        val iosArm64Main by getting {
            dependsOn(iosMain)
        }
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }

        val desktopMain by getting {
            dependsOn(commonMain)

            dependencies {
                implementation(compose.desktop.common)
            }
        }

        val jsMain by getting {
            dependsOn(commonMain)

            dependencies {
                implementation(compose.html.core)
            }
        }
    }
}

val appPackageName = project.property("maksimillano.package").toString()

android {
    compileSdk = 34
    namespace = "$appPackageName.shared"
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
    sourceSets["main"].resources.exclude("src/commonMain/resources/MR")

    defaultConfig {
        minSdk = 26
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:2024.03.00"))
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

buildkonfig {
    packageName = appPackageName

    defaultConfigs {
        buildConfigField(FieldSpec.Type.STRING, "VERSION", project.property("maksimillano.appVersion").toString())
    }
}

multiplatformResources {
    multiplatformResourcesPackage = appPackageName
}

tasks.withType<KotlinCompile>() {
    compilerOptions.freeCompilerArgs.addAll(
        "-P",
        "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=${project.buildDir.path}/compose_metrics",
    )
}
