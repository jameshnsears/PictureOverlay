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

rootProject.name = "Picture Overlay"
include(":app")
include(":opencv")
project(":opencv").projectDir = File(rootDir, "module/opencv/")
include(":common")
project(":common").projectDir = File(rootDir, "module/common/")
include(":permissions")
project(":permissions").projectDir = File(rootDir, "module/permissions/")
