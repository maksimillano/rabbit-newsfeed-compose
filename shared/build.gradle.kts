import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.botsystems.toolkits.core.CorePlugin
import org.botsystems.toolkits.core.exts.IconResizeExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")

    kotlin("plugin.serialization") version "2.0.0-Beta1"

    id("kotlin-parcelize") // for decompose

    id("dev.icerock.mobile.multiplatform-resources")
    id("com.codingfeline.buildkonfig")
}

buildscript{
    repositories{
    }

    dependencies {
        classpath("net.coobird:thumbnailator:0.4.17") // for toolkits plugin
        classpath(
            files(
                "../tools/gradle/toolkits-1.0.23.jar",
            )
        )
    }
}

apply<CorePlugin>()
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev") // decompose-router
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/") // decompose-router
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
        val commonMain by getting {
            dependencies {
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                // Coroutines
                implementation(libs.kotlinx.coroutines)
                // Decompose
                implementation(libs.decompose)
                implementation(libs.decompose.jetbrains.extensions)
                // DI
                implementation(libs.koin.core)

                // Files storage
                implementation(libs.korge.core)
                // LRU Cache
                implementation(libs.cache4k)

                // Image loader + LRU cache
                implementation(libs.kamel)

                // Moko resources
                api(libs.moko.resources)
                api(libs.moko.resources.compose)

                // DateTime
                implementation(libs.kotlinx.datetime)
                // Logging
                implementation(libs.kermit)
                // Serialization
                implementation(libs.kotlinx.serialization.json)
                // Versioning
                implementation(libs.semver)
            }
        }

        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                api(libs.androidx.activity.compose)
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.10.1")

                // Compose Preview
                implementation("androidx.compose.ui:ui-tooling-preview:1.5.4")
            }
        }

        val androidUnitTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }

        val iosMain by creating {
            dependsOn(commonMain)
            dependencies {
            }
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
    namespace = "$appPackageName.android.shared"
    sourceSets["main"].manifest.srcFile("src/apps/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/apps/androidMain/res")
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

configure<IconResizeExtension> {
    val baseProjPath = project.parent!!.projectDir.path
    val currProjPath = project.projectDir.path

    stagingDir = "$baseProjPath/stagingIcons"
    resourceDir = "$currProjPath/src/commonMain/resources/MR/images"
}

tasks.withType<KotlinCompile> {
    compilerOptions.freeCompilerArgs.addAll(
        "-P",
        "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=${project.buildDir.path}/compose_metrics",
    )
}