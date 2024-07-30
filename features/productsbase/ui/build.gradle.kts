plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "idea.platform.ui"
    compileSdk = libs.versions.androidSdk.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.androidSdk.min.get().toInt()
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.material3)

    implementation(platform(libs.androidx.compose.bom))

    api(libs.androidx.compose.ui.tooling.preview)

    implementation(libs.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(libs.viewmodel.ktx)
    implementation(libs.viewmodel.compose)

    implementation(libs.koin)
    implementation(libs.koin.compose)

    implementation(projects.core.common)
    implementation(projects.core.uikit)
    implementation(projects.features.productsbase.viewmodel)
}