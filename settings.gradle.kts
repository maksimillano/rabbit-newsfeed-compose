pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    plugins {
        val kotlinVersion = extra["kotlin.version"] as String
        val agpVersion = extra["agp.version"] as String
        val composeVersion = extra["compose.version"] as String
        val mokoResourcesVersion = extra["moko.resources.version"] as String
        val buildkonfig = extra["buildkonfig.version"] as String

        kotlin("jvm").version(kotlinVersion)
        kotlin("multiplatform").version(kotlinVersion)
        kotlin("android").version(kotlinVersion)
        id("com.android.base").version(agpVersion)
        id("com.android.application").version(agpVersion)
        id("com.android.library").version(agpVersion)
        id("org.jetbrains.compose").version(composeVersion)

        id("dev.icerock.mobile.multiplatform-resources").version(mokoResourcesVersion)
        id("com.codingfeline.buildkonfig").version(buildkonfig)
    }
}

dependencyResolutionManagement {
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version("0.4.0")
}

rootProject.name = "rabbit-newsfeed-compose"

include(":shared")
include(":apps")
include(":apps:androidApp")
include(":apps:desktopApp")
include(":apps:jsApp")