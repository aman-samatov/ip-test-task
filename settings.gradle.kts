enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ip-test-task"
include(":app")

include(":core:uikit")
include(":core:common")

include(":features:productdetails:ui")
include(":features:productdetails:viewmodel")
include(":features:productdetails:api")
include(":features:productdetails:data")

include(":features:productsbase:ui")
include(":features:productsbase:viewmodel")
include(":features:productsbase:api")
include(":features:productsbase:data")
