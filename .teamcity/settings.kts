import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script

version = "2023.05"

project {

    subProject(Project22)
    subProject(Project21)
}


object Project21 : Project({
    name = "project21"
params {
    param("par1", "1")
}
    buildType(Project21_Build1)
    buildType(Project21_Build3)
})

object Project21_Build1 : BuildType({
    name = "build1"

    vcs {
        root(DslContext.settingsRoot)
    }
    steps {
        script {
            scriptContent = "echo %par1%"
        }
    }
   dependencies {
        snapshot(Project21_Build3) {
            reuseBuilds = ReuseBuilds.NO
        }
    }
})

object Project21_Build3 : BuildType({
    name = "build3"
    steps {
        script {
            scriptContent = "echo %par1%"
        }
    }
    vcs {
        root(DslContext.settingsRoot)
    }
})


object Project22 : Project({
    name = "project22"

    buildType(Project22_Build2)
buildType(Project22_Build3)
})

object Project22_Build2 : BuildType({
    name = "build2"

    vcs {
        root(DslContext.settingsRoot)
    }
   dependencies {
        snapshot(Project22_Build3) {
            reuseBuilds = ReuseBuilds.NO
        }
    }
})

object Project22_Build3 : BuildType({
    name = "build3"
    steps {
        script {
            scriptContent = "ls"
        }
    }
    vcs {
        root(DslContext.settingsRoot)
    }
})
