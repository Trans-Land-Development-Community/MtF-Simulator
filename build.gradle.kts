plugins {
    kotlin("js") version "1.5.30"
}

group = "me.xiaro"
version = "1.0.0"

repositories {
    jcenter()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

kotlin {
    js(LEGACY) {
        compilations.all {
            compileKotlinTask.kotlinOptions.freeCompilerArgs += listOf("-Xopt-in=kotlin.RequiresOptIn")
        }
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-html:0.7.2")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react:17.0.2-pre.241-kotlin-1.5.30")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:17.0.2-pre.241-kotlin-1.5.30")
    implementation(npm("react", "17.0.2"))
    implementation(npm("react-dom", "17.0.2"))
    implementation(npm("react-is", "17.0.2"))

    implementation("org.jetbrains.kotlin-wrappers:kotlin-styled:5.3.1-pre.241-kotlin-1.5.30")
    implementation(npm("styled-components", "~5.3.1"))
}