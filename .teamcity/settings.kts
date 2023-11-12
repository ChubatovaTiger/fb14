import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2023.05"

project {

    vcsRoot(Repo3)

    subProject(Project22)
    subProject(Project21)
}

object Repo3 : GitVcsRoot({
    name = "repo3"
    url = "git@github.com:ChubatovaTiger/repo3.git"
    branch = "refs/heads/main"
    authMethod = uploadedKey {
        uploadedKey = "rsaopensshnew"
        passphrase = "credentialsJSON:92710fb4-18c3-40fe-a2fa-1b14c42b8014"
    }
})


object Project21 : Project({
    name = "project21"

    buildType(Project21_Build1)
buildType(Project21_Build3)
    params {
        param("par1", "1")
    }
})

object Project21_Build1 : BuildType({
    name = "build1"

    vcs {
        root(DslContext.settingsRoot)
        root(Repo3, "+:. => repo3")
    }
   dependencies {
        snapshot(Project21_Build3) {
            reuseBuilds = ReuseBuilds.NO
        }
    }
})


object Project21_Build3 : BuildType({
    name = "build3"

    vcs {
        root(DslContext.settingsRoot)
        root(Repo3, "+:. => repo3")
    }
})

object Project22 : Project({
    name = "project22"

    buildType(Project22_Build2)
})

object Project22_Build2 : BuildType({
    name = "build2"

    vcs {
        root(DslContext.settingsRoot)
    }
})
